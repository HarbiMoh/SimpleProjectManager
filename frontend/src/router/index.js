import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import DisplayResouces from '../views/DisplayResources.vue'
import DisplayTasks from '../views/DisplayTasks.vue'
import DisplayTasksAndResources from '../views/DisplayTasksAndResources.vue'
import DisplayTasksTotalCost from '../views/DisplayTasksTotalCost.vue'
import DisplayProjectTotalCost from '../views/DisplayProjectTotalCost.vue'
import CreateResource from '../views/CreateResource.vue'
import CreateTask from '../views/CreateTask.vue'
import UserLogin from '../views/UserLogin.vue'
import UserSignup from '../views/UserSignup.vue'
import MainView from '../views/MainView.vue'
import CreateProject from '../views/CreateProject.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'main',
      component: MainView
    },
    {
      path: '/:username',
      name: 'home',
      component: HomeView
    },
    {
      path: '/:username/:projectId',
      name: 'home_with_proejct_selected',
      component: HomeView
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue')
    },
    {
      path: '/:username/create/project',
      name: 'project',
      component: CreateProject
    },
    {
      path: '/:username/:projectId/display/resources',
      name: 'resources',
      component: DisplayResouces
    },
    {
      path: '/:username/:projectId/display/tasks',
      name: 'tasks',
      component: DisplayTasks
    },
    {
      path: '/:username/:projectId/display/tasks_and_resouces',
      name: 'tasks_and_resouces',
      component: DisplayTasksAndResources
    },
    {
      path: '/:username/:projectId/display/tasks_total_cost',
      name: 'tasks_total_cost',
      component: DisplayTasksTotalCost
    },
    {
      path: '/:username/:projectId/display/project_total_cost',
      name: 'project_total_cost',
      component: DisplayProjectTotalCost
    },
    {
      path: '/:username/:projectId/create/resource',
      name: 'create_resource',
      component: CreateResource
    },
    {
      path: '/:username/:projectId/create/task',
      name: 'create_task',
      component: CreateTask
    },
    {
      path: '/login',
      name: 'login',
      component: UserLogin
    },
    {
      path: '/signup',
      name: 'signup',
      component: UserSignup
    }
  ]
})

export default router
