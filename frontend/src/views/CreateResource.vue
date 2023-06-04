<script>
import axios from 'axios'
export default {
  data() {
    return {
      resourceName: undefined,
      type: 'COST',
      cost: 0,
      availableResources: []
    }
  },
  methods: {
    sendRequest: function () {
      console.log(this.start)
      const data = {
        name: this.resourceName,
        type: this.type,
        cost: this.cost
      }
      console.log(data)
      axios({
        url: `http://localhost:8080/api/v1/${this.$route.params.username}/${this.$route.params.projectId}/resource/create`,
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
    <h2>Create Resource</h2>
    <form>
      <div class="form-group">
        <label for="task_name">Resource Name:</label>
        <input
          type="text"
          v-model="resourceName"
          class="form-control"
          id="task_name"
          placeholder="Enter Resouce Name"
          name="task_name"
        />
      </div>
      <div class="form-group">
        <label for="duration">Type:</label>
        <select class="form-select" v-model="type" aria-label="Default select example">
          <option selected value="COST">COST</option>
          <option value="MATERIAL">MATERIAL</option>
          <option value="WORK">WORK</option>
        </select>
      </div>
      <div class="form-group" v-show="type != 'MATERIAL'">
        <label for="duration">cost:</label>
        <input type="number" class="form-control" v-model="cost" />
      </div>
      <button type="button" @click="sendRequest" class="btn btn-default">Submit</button>
    </form>
  </div>
</template>
