import Vue from 'vue'
import Router from 'vue-router'
import Test from '@/components/Test'
import Verwaltung from '@/components/Verwaltung'
import Hello from '@/components/Hello'
import Service from '@/components/Service'
import Bootstrap from '@/components/Bootstrap'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Test',
      component: Test
    },
    {
      path: '/',
      name: 'Verwaltung',
      component: Verwaltung
    },
    {
      path: '/',
      name: 'Hello',
      component: Hello
    },

    {
      path: '/callservice',
      name: 'Service',
      component: Service
    },
    {
      path: '/bootstrap',
      name: 'Bootstrap',
      component: Bootstrap
    }
  ]
})
