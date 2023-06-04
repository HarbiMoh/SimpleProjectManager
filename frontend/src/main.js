import './assets/main.css'
import './assets/style.default.css'
import './assets/custom.css?v=323223'
import './assets/fontastic.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'font-awesome/css/font-awesome.min.css'
import 'bootstrap'
import '@fancyapps/ui/dist/fancybox/fancybox.css'

const app = createApp(App)

app.config.globalProperties.username = 'moh'
app.config.globalProperties.projectId = 1

app.use(router)

app.mount('#app')
