<template>
  <div id="notice">
    <Modal v-model="showNotice" :closable="false" :mask-closable="false">
      <p slot="header" style="color:#843534;text-align:left">
        <span>错误</span>
      </p>
      <div style="text-align:center">
        <span>{{infoname}}</span>
      </div>
      <div slot="footer">
        <Button type="primary" @click="cancel()">确定</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
  export default {
    name: 'notice',
    data () {
      return {
        showNotice: true
      }
    },
    props: ['infoname'],
    methods: {
      cancel () {
        this.$emit('closeModal')
      }
    }
  }
</script>
