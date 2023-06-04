<script>
import axios from 'axios'
export default {
  data() {
    return {
      projectName: undefined,
      type: 'COST',
      availableResources: []
    }
  },
  methods: {
    sendRequest: function () {
      console.log(this.start)
      const data = {
        name: this.projectName,
        type: this.type
      }
      console.log(data)
      axios({
        url: `http://localhost:8080/api/v1/${this.$route.params.username}/project/create`,
        headers: {
          'Content-Type': 'application/json'
        },
        method: 'post',
        data: data
      }).then((response) => {
        console.log(response)
        alert('saved!')
      })
    }
  }
}
</script>

<template>
  <div class="container" style="width: 35%">
    <h2>Create Project</h2>
    <form>
      <div class="form-group">
        <label for="task_name">Project Name:</label>
        <input
          type="text"
          v-model="projectName"
          class="form-control"
          id="task_name"
          placeholder="Enter Project Name"
          name="task_name"
        />
      </div>
      <button type="button" @click="sendRequest" class="btn btn-default">Submit</button>
    </form>
  </div>
</template>
