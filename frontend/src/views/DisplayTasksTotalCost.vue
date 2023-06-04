<script>
import axios from 'axios'

export default {
  data() {
    return {
      tasks: [],
      noTasksFound: false
    }
  },
  methods: {
    deleteTask: function (id) {
      axios({
        url: `http://localhost:8080/api/v1/${this.$route.params.username}/${this.$route.params.projectId}/task/${id}/delete`,
        responseType: 'json'
      }).then(() => {
        console.log('deleted')
        this.tasks = []
        this.getTasks()
        alert('deleted succesfully!')
      })
    },
    getTasks: function () {
      axios({
        url: `http://localhost:8080/api/v1/${this.$route.params.username}/${this.$route.params.projectId}/task`,
        responseType: 'json'
      }).then((response) => {
        console.log(response)
        if (response.data.length === 0) this.noTasksFound = true
        this.tasks = response.data
      })
    },
    calculateDuration: function (start, end) {
      const date1 = new Date(start)
      const date2 = new Date(end)
      const diffTime = Math.abs(date2 - date1)
      const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24))
      return diffDays
    }
  },
  mounted() {
    this.getTasks()
  }
}
</script>

<template>
  <div v-if="tasks.length == 0 && !noTasksFound" class="spinner-border my-center" role="status">
    <span class="sr-only">Loading...</span>
  </div>
  <div v-else-if="noTasksFound">
    <div class="my-center">no tasks for this project</div>
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
                  <th>Task Name</th>
                  <th>Task Type</th>
                  <th>Duration</th>
                  <th>Start (Date)</th>
                  <th>Finish (Date)</th>
                  <th>Resource name</th>
                  <th>Total Cost</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="task in tasks" :key="task.id">
                  <td></td>
                  <td>{{ task.name }}</td>
                  <td>{{ task.type }}</td>
                  <td>{{ calculateDuration(task.start, task.end) }}</td>
                  <td>{{ task.start }}</td>
                  <td>{{ task.end }}</td>
                  <td>
                    <span v-for="resource in task.resources" :key="resource"
                      >{{ resource.resource_name }},</span
                    >
                  </td>
                  <td>{{ task.cost }}</td>
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
