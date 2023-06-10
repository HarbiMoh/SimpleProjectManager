<script>
import axios from 'axios'
import router from '../router'

export default {
  data() {
    return {
      usernameOrEmail: null,
      password: null
    }
  },
  methods: {
    sendRequest: function () {
      console.log(this.start)
      const data = {
        account_id: this.usernameOrEmail,
        password: this.password
      }
      console.log(data)
      axios({
        url: `http://localhost:8080/api/v1/auth/login`,
        headers: {
          'Content-Type': 'application/json'
        },
        method: 'post',
        data: data
      }).then(
        (response) => {
          console.log(response)
          router.push({ path: `/${response.data.username}` })
          alert('Logged!')
        },
        (response) => {
          console.log(response)
          alert('username, email or password are wrong')
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
              <label for="email">username or email address</label>
              <input
                name="email"
                type="email"
                class="form-control"
                v-model="usernameOrEmail"
                id="email"
                placeholder="Enter email or username"
              />
            </div>
            <div class="form-group">
              <label for="password">Password</label>
              <input
                name="password"
                type="password"
                v-model="password"
                class="form-control"
                id="password"
                placeholder="Password"
              />
            </div>
            <input type="hidden" name="signIn" />
            <button type="button" @click="sendRequest" class="btn btn-primary">Sign In</button>
          </form>
        </div>
      </div>
    </div>
  </section>
</template>
