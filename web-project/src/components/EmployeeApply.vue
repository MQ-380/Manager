<template>
  <div id="apply">
    <apply v-if="showApply" @closeSuccess="closeSuccess" @cancel="cancel"></apply>
    <Button type="success" @click="newApply" style="float: left">新建外出申请</Button>
  </div>
</template>

<script>
  import apply from './EmployeeNewApply'

  export default {
    data () {
      return {
        showApply: false
      }
    },
    methods: {
      newApply () {
        this.showApply = true
      },
      closeSuccess () {
        this.showApply = false
      },
      cancel () {
        this.showApply = false
      }
    },
    components: {
      apply
    }

  }
</script>
