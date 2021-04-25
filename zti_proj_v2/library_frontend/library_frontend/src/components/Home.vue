<template>
  <div class="container">
    <navbar></navbar>
    <b-jumbotron class="jumbotron jumbotron-home">
      
       <Footer></Footer>

     </b-jumbotron>
  </div>
</template>



<script>
import Navbar from '../components/Navbar.vue';
import Footer from '../components/Footer.vue';
// import axios from 'axios';
export default {
  name: "Home",
  components: {
    Navbar,
    Footer,
  },
  data() {
    return {
      form: {

      },
    }
  },
  mounted: function () {
  },



  methods:{

  }
};
</script>

<style scoped>
@import '../../styles/style.css';

.custom {
    width: 73px !important;
}
.custom2 {
    width: 219px!important;
}


.myform1{
  padding: 0%;
  margin-left: 0%;
  margin-right: 0.3%;
  text-align: left;
}
.myform2{
  padding: 0%;
  margin: 0%;
  text-align: left;

}
@media (min-width: 100px) {
    .container{
        max-width: 1400px;
    }
}
.jumbotron-home{
    margin: 0%;
    padding: 1%;
    padding-left:0.5%;
    padding-right:0.5%;

}
</style>
