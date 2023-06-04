<script>
import axios from 'axios'

export default {
  data() {
    return {
      projects: [],
      noProjectsFound: false
    }
  },
  methods: {
    getProjects: function () {
      axios({
        url: `http://localhost:8080/api/v1/${this.$route.params.username}/project`,
        responseType: 'json'
      }).then((response) => {
        console.log(response)
        if (response.data.length === 0) this.noProjectsFound = true
        this.projects = response.data
      })
    },
    deleteProject: function (id) {
      axios({
        url: `http://localhost:8080/api/v1/${this.$route.params.username}/project/${id}/delete`,
        responseType: 'json'
      }).then(() => {
        console.log('deleted')
        this.$emit('projectChanged', '')
        this.getProjects()
        alert('deleted succesfully!')
      })
    }
  },

  mounted() {
    this.getProjects()
  }
}
</script>

<template>
  <main>
    <div
      v-if="projects.length == 0 && !noProjectsFound"
      class="spinner-border my-center"
      role="status"
    >
      <span class="sr-only">Loading...</span>
    </div>
    <div v-else-if="noProjectsFound">
      <div class="my-center">no projects for this user, create project</div>
    </div>
    <section v-else class="dashboard">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <div class="table-responsive">
              <table
                id="showBlogs"
                class="table table-striped table-bordered"
                style="width: 30%; margin: 0 auto"
              >
                <thead>
                  <tr>
                    <th>Project Name</th>
                    <th style="width: 205px">Actions</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="project in projects" :key="project.id">
                    <td>{{ project.name }}</td>
                    <td style="width: 40%">
                      <!-- <a href="#" class="btn btn-info">Edit</a> -->
                      <a
                        href="#"
                        @click="$emit('projectChanged', project.id)"
                        class="btn btn-success"
                        >select</a
                      >
                      <a @click="deleteProject(project.id)" class="btn btn-danger">Delete</a>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- <section class="dashboard">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div style="padding: 32px;box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px; background-color: #E7FFFF">
                    <h5 class="text-black-50 text-center">Welcome! Please sign in or sign up to proceed</h5>
                    <br>
                    <div class="text-center">
                        <RouterLink to="/login" class="w-full"><button class="btn btn-primary" >Login</button></RouterLink>
                        <br>
                        <br>
                        <RouterLink to="/signup" class="w-full"><button class="btn btn-primary" >Register</button></RouterLink>
                    </div>


                </div>
            </div>

        </div>
    </div>
</section> -->
  </main>
</template>

<style scoped>
.my-center {
  display: block;
  margin: 4rem auto;
  text-align: center;
}
</style>
