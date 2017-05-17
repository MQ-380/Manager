<template>
  <div id="success">
    <Modal v-model="showNotice" :closable="false" :mask-closable="false">
      <p slot="header" style="color:#843534;text-align:left">
        <span>成功</span>
      </p>
      <div style="text-align:center">
        <span>{{msg}}</span>
      </div>
      <div slot="footer">
        <Button type="primary" @click="cancel()">确定</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
  export default {
    name: 'success',
    data () {
      return {
        showNotice: true
      }
    },
    props: ['msg'],
    methods: {
      cancel () {
        this.$emit('closeModal')
      }
    }
  }
</script>
