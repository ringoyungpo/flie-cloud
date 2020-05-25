import Vue from 'vue'
import VueRouter from 'vue-router'
import View from '../views/View.vue'
import New from '../views/New.vue'
import Editor from '../views/Editor.vue'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/view',
    name: 'View',
    component: View
  },
  {
    path: '/editor/:id',
    name: 'Editor',
    component: Editor
  },
  {
    path: '/',
    name: 'New',
    component: New
  }
]

const router = new VueRouter({
  routes
})

export default router
