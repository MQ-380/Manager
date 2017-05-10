var Foo = Vue.extend({
    template: require('!html-loader!../Adminheader.html'),
    props: {
        call: {
            type: Object,
            required: true
        }
    },
});

new Foo().$mount('#header');
