<template>
  <div>
    <b-alert
      variant="danger"
      dismissible
      fade
      :show="showCredentialsAlert"
      @dismissed="showCredentialsAlert=false"
    >
      Invalid password or username.
    </b-alert>
    <b-jumbotron class="jumbotron jumbotron-special" header=" ">
        <h1> </h1>
        <h1> </h1>
        <h1> </h1>
        <h1> </h1>
    </b-jumbotron>
  </div>
</template>



<script>
// import axios from 'axios';
  export default {
    name: "Navbar",
    components:{

    },
    data(){
      return{
        
      }
    },
    mounted: function (){
      
    },
    methods: {
     
    
    }
  }
</script>

<style scoped>
.jumbotron-special{
  background-image: url("../../static/bidbackground.jpg");
  background-size: cover;
  margin-bottom: 0.5%;

  padding-bottom: 1%;
}
.jumbotron h1{
  text-align: center;
  padding: 1%;
}

</style>
