// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

import { Button, Table, Carousel, CarouselItem, Input, Menu, MenuItem, Affix, Poptip, Icon } from 'iview'
import 'iview/dist/styles/iview.css'

Vue.component('Button', Button)
Vue.component('Table', Table)
Vue.component('CarouselItem', CarouselItem)
Vue.component('Carousel', Carousel)
Vue.component('Input', Input)
Vue.component('Menu', Menu)
Vue.component('MenuItem', MenuItem)
Vue.component('Affix', Affix)
Vue.component('Poptip', Poptip)
Vue.component('Icon', Icon)
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
