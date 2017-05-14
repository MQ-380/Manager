<template>
  <div id="notice">
    <Modal v-model="showNotice" @on-ok="changeShow">
      <p slot="header" style="color:#843534;text-align:left">
        <span>请检查您的网络情况</span>
      </p>
      <div style="text-align:center">
        <span>无法获得{{infoName}}信息，请检查您的网络状况</span>
      </div>
    </Modal>
  </div>
</template>

<script>
  export default {
    name: 'notice',
    data () {
      return {
        infoName: this.$store.state.noticeState.infoName
      }
    },
    computed: {
      showNotice: function () {
        return this.$store.state.noticeState.notice
      }
    },
    methods: {
      changeShow () {
        this.$store.commit('SETNOTICE', {
          name: '',
          show: false
        })
      }
    }
  }
</script>
