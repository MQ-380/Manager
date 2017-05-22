<template>
  <Modal v-model="notice" @on-ok="LogoutAction">
    <p slot="header" style="color:#843534;text-align:left">
      <span>注意</span>
    </p>
    <div style="text-align:center">
      <span>请确定是否要登出账户？</span>
    </div>
  </Modal>
</template>

<script>
  export default {
    data () {
      return {
        notice: true
      }
    },
    methods: {
      LogoutAction () {
        this.$store.commit('LOGOUT')
        this.$router.push('/')
      }
    }
  }
</script>
