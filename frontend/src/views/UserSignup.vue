<script>
import axios from 'axios'
import router from '../router'

export default {
  data() {
    return {
      fullname: null,
      email: null,
      signupUsername: null,
      password: null
    }
  },
  methods: {
    sendRequest: function () {
      console.log(this.start)
      const data = {
        name: this.fullname,
        email: this.email,
        username: this.signupUsername,
        password: this.password
      }
      console.log(data)
      axios({
        url: `http://localhost:8080/api/v1/auth/signup`,
        headers: {
          'Content-Type': 'application/json'
        },
        method: 'post',
        data: data
      }).then(
        (response) => {
          console.log(response)
          router.push({ path: `/${this.signupUsername}` })
          this.$emit('usernameChange', this.signupUsername)
          alert('Logged!')
        },
        (response) => {
          console.log(response)
          alert('username or email exists')
        }
      )
    }
  }
}
</script>

<template>
  <section class="dashboard" style="width: 30%; margin: 0 auto; display: block">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <form
            method="post"
            action=""
            style="
              padding: 32px;
              box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
              background-color: #e7ffff;
            "
          >
            <div class="form-group">
              <label for="name">Name</label>
              <input
                name="name"
                v-model="fullname"
                type="text"
                class="form-control"
                id="name"
                placeholder="Enter name"
              />
            </div>
            <div class="form-group">
              <label for="email">Email address</label>
              <input
                name="email"
                v-model="email"
                type="email"
                class="form-control"
                id="email"
                placeholder="Enter email"
              />
            </div>
            <div class="form-group">
              <label for="username">Username</label>
              <input
                name="username"
                v-model="signupUsername"
                class="form-control"
                id="username"
                placeholder="Enter username"
              />
            </div>
            <div class="form-group">
              <label for="password">Password</label>
              <input
                name="password"
                v-model="password"
                type="password"
                class="form-control"
                id="password"
                placeholder="Enter password"
                aria-describedby="passwordHelp"
              />
              <small id="passwordHelp" class="form-text text-muted"
                >Password must be between 6 and 32 characters long.</small
              >
            </div>
            <input type="hidden" name="signUp" />
            <button type="button" @click="sendRequest" class="btn btn-primary">Sign Up</button>
          </form>
        </div>
      </div>
    </div>
  </section>
</template>
