<script>
import axios from 'axios'

export default {
  data() {
    return {
      resources: [],
      noResourcesFound: false
    }
  },
  methods: {
    deleteResource: function (id) {
      axios({
        url: `http://localhost:8080/api/v1/${this.$route.params.username}/${this.$route.params.projectId}/resource/${id}/delete`,
        responseType: 'json'
      }).then(() => {
        console.log('deleted')
        this.resources = []
        this.getResouces()
        alert('deleted succesfully!')
      })
    },
    getResouces: function () {
      axios({
        url: `http://localhost:8080/api/v1/${this.$route.params.username}/${this.$route.params.projectId}/resource`,
        responseType: 'json'
      }).then((response) => {
        console.log(response)
        if (response.data.length === 0) this.noResourcesFound = true
        this.resources = response.data
      })
    }
  },
  mounted() {
    this.getResouces()
  }
}
</script>

<template>
  <div
    v-if="resources.length == 0 && !noResourcesFound"
    class="spinner-border my-center"
    role="status"
  >
    <span class="sr-only">Loading...</span>
  </div>
  <div v-else-if="noResourcesFound">
    <div class="my-center">no resources for this project</div>
  </div>
  <section v-else class="dashboard">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="table-responsive">
            <table id="showBlogs" class="table table-striped table-bordered" style="width: 100%">
              <thead>
                <tr>
                  <th>#</th>
                  <th>Resource Name</th>
                  <th>Type (List)</th>
                  <th>Material</th>
                  <th>Max (No. of resources)</th>
                  <th>St.Rate</th>
                  <th>Ovt.</th>
                  <th>Cost/Use</th>

                  <th style="width: 205px">Actions</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="resource in resources" :key="resource.id">
                  <td></td>
                  <td>{{ resource.name }}</td>
                  <td>{{ resource.type }}</td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td>
                    {{ resource.type == 'MATERIAL' ? '' : resource.cost
                    }}{{ resource.type == 'WORK' ? '/hr' : '' }}
                  </td>
                  <td>
                    <!-- <a href="#" class="btn btn-info">Edit</a>
                    <a href="#" class="btn btn-success">View</a> -->
                    <a @click="deleteResource(resource.id)" class="btn btn-danger">Delete</a>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped>
.my-center {
  display: block;
  margin: 4rem auto;
  text-align: center;
}
</style>
