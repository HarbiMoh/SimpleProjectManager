<script>
import axios from 'axios'
export default {
  data() {
    return {
      taskName: undefined,
      order: undefined,
      start: undefined,
      end: undefined,
      type: 'TASK',
      resources: {
        resourcesId: [],
        costs: []
      },
      availableResources: [],
      noResourcesFound: false,
      numOfResources: 1
    }
  },
  mounted() {
    axios({
      url: `http://localhost:8080/api/v1/${this.$route.params.username}/${this.$route.params.projectId}/resource`,
      responseType: 'json'
    }).then((response) => {
      console.log(response)
      if (response.data.length === 0) this.noResourcesFound = true
      this.availableResources = response.data
    })
  },
  methods: {
    sendRequest: function () {
      console.log(this.start)
      var data = {}
      console.log(this.resources.resourcesId.length)
      if (this.resources.resourcesId == null) {
        data = {
          name: this.taskName,
          type: this.type,
          order: this.order,
          start: this.start,
          end: this.end
        }
      } else {
        data = {
          name: this.taskName,
          type: this.type,
          order: this.order,
          start: this.start,
          end: this.end,
          resources: this.resources
        }
      }
      console.log(data)
      console.log(JSON.stringify(data))
      axios({
        url: `http://localhost:8080/api/v1/${this.$route.params.username}/${this.$route.params.projectId}/task/create`,
        headers: {
          'Content-Type': 'application/json'
        },
        method: 'post',
        data: JSON.stringify(data)
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
    <h2>Create Task</h2>
    <form>
      <div class="form-group">
        <label for="task_name">Task Name:</label>
        <input
          type="text"
          v-model="taskName"
          class="form-control"
          id="task_name"
          placeholder="Enter Task Name"
          name="task_name"
        />
      </div>
      <div class="form-group">
        <label for="task_id">Task order:</label>
        <input
          type="number"
          v-model="order"
          class="form-control"
          id="task_id"
          placeholder="Enter Task order"
          name="task_id"
        />
      </div>
      <!-- <div class="form-group">
                <label for="duration">Type:</label>
                <select class="form-select" v-model="type" aria-label="Default select example">
                    <option selected value="COST">COST</option>
                    <option value="MATERIAL">MATERIAL</option>
                    <option value="WORK">WORK</option>
                </select>
            </div> -->
      <div class="form-group">
        <label for="start_date">Start Date:</label>
        <input type="date" v-model="start" class="form-control" id="start_date" name="start_date" />
      </div>
      <div class="form-group">
        <label for="finish_date">Finish Date:</label>
        <input type="date" v-model="end" class="form-control" id="finish_date" name="finish_date" />
      </div>
      <div v-for="i in numOfResources" :key="i">
        <div class="form-group" style="width: 70%; display: inline-block">
          <label for="duration">add Resources:</label>
          <select
            class="form-select"
            @change="i == numOfResources ? ++numOfResources : null"
            v-model="resources.resourcesId[i - 1]"
            aria-label="Default select example"
          >
            <option v-for="resource in availableResources" :value="resource.id" :key="resource.id">
              {{ resource.name }}
            </option>
          </select>
        </div>
        <div class="form-group" style="width: 30%; display: inline-block">
          <label for="duration">Resouce value:</label>
          <input type="text" v-model="resources.costs[i - 1]" class="form-control" />
        </div>
      </div>
      <button type="button" @click="sendRequest" class="btn btn-default">Submit</button>
    </form>
  </div>
</template>
