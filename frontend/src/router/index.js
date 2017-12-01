import Vue from 'vue'
import Router from 'vue-router'
import Test from '@/components/Test'
import Verwaltung from '@/components/Verwaltung'
import Hello from '@/components/Hello'
import Service from '@/components/Service'
import Bootstrap from '@/components/Bootstrap'
import Login from '@/components/Login'
import Teilnehmer from '@/components/Teilnehmer'
import Reservierung from '@/components/Reservierung'
import Veranstaltung from '@/components/Veranstaltung'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/Reservierung',
      name: 'Reservierung',
      component: Reservierung
    },
    {
      path: '/Teilnehmer',
      name: 'Teilnehmer',
      component: Teilnehmer
    },
    {
      path: '/Veranstaltung',
      name: 'Veranstaltung',
      component: Veranstaltung
    },

    {
      path: '/Test',
      name: 'Test',
      component: Test
    },
    {
      path: '/Verwaltung',
      name: 'Verwaltung',
      component: Verwaltung
    },
    {
      path: '/Hello',
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
