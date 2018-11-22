//路由配置
import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
import home from '@/components/home'
import detail from '@/components/detail'
import more from '@/components/more'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/', component: home
    },
    { path: '/detail/:id', component: detail },
    { path: '/more', component: more }
  ]
})
