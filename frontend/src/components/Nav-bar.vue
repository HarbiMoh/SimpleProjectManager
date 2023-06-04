<script>
export default {
  props: ['username', 'projectId'],
  mounted() {
    console.log(this.username)
  }
}
</script>

<template>
  <header class="header">
    <!-- Main Navbar-->
    <nav class="navbar navbar-expand-lg" style="width: 100%">
      <div class="search-area">
        <div class="search-area-inner d-flex align-items-center justify-content-center">
          <div class="close-btn"><i class="icon-close"></i></div>
          <div class="row d-flex justify-content-center">
            <div class="col-md-8">
              <form action="#">
                <div class="form-group">
                  <input
                    type="search"
                    name="search"
                    id="search"
                    placeholder="What are you looking for?"
                  />
                  <button type="submit" class="submit"><i class="icon-search-1"></i></button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
      <div class="container">
        <!-- Navbar Brand -->
        <div class="navbar-header d-flex align-items-center justify-content-between">
          <!-- Navbar Brand --><a href="#" class="navbar-brand">Project Mangement</a>
          <!-- Toggle Button-->
          <button
            type="button"
            data-toggle="collapse"
            data-target="#navbarcollapse"
            aria-controls="navbarcollapse"
            aria-expanded="false"
            aria-label="Toggle navigation"
            class="navbar-toggler"
          >
            <span></span><span></span><span></span>
          </button>
        </div>
        <!-- Navbar Menu -->
        <div id="navbarcollapse" class="collapse navbar-collapse">
          <ul class="navbar-nav ml-auto" v-if="username != null && username != ''">
            <li class="nav-item">
              <RouterLink :to="`/${username}`" class="nav-link active"> Home </RouterLink>
            </li>
            <li class="nav-item">
              <RouterLink :to="`/${username}/create/project`" class="nav-link active">
                Create Project
              </RouterLink>
            </li>

            <div
              v-if="projectId != null && projectId != '' && projectId != undefined"
              style="display: flex"
            >
              <li class="nav-item">
                <RouterLink
                  :to="`/${username}/${projectId}/display/project_total_cost`"
                  class="nav-link active"
                >
                  project total cost
                </RouterLink>
              </li>

              <li class="nav-item">
                <RouterLink
                  :to="`/${username}/${projectId}/display/tasks_total_cost`"
                  class="nav-link active"
                >
                  display TasksTotalCost
                </RouterLink>
              </li>
              <li class="nav-item">
                <RouterLink
                  :to="`/${username}/${projectId}/display/resources`"
                  class="nav-link active"
                >
                  display resources
                </RouterLink>
              </li>

              <li class="nav-item">
                <RouterLink
                  :to="`/${username}/${projectId}/create/resource`"
                  class="nav-link active"
                >
                  create resource
                </RouterLink>
              </li>

              <li class="nav-item">
                <RouterLink :to="`/${username}/${projectId}/display/tasks`" class="nav-link active">
                  display tasks
                </RouterLink>
              </li>

              <li class="nav-item">
                <RouterLink :to="`/${username}/${projectId}/create/task`" class="nav-link active">
                  create task
                </RouterLink>
              </li>

              <li class="nav-item">
                <RouterLink
                  :to="`/${username}/${projectId}/display/tasks_and_resouces`"
                  class="nav-link active"
                >
                  task and resouces</RouterLink
                >
              </li>
            </div>
            <li class="nav-item"><a @click="$emit('logout', '')" class="nav-link">Logout</a></li>
          </ul>
        </div>
      </div>
    </nav>
  </header>
</template>
