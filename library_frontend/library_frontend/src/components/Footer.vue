<template>
  <div>
    <b-modal id="RegisterModal" size="lg" title="Regulations of the library portal">
      <td id="mytext" v-html="regulations"></td>
    </b-modal>
    <div id="container2">
     <div id="contacts">
       <div id="contacts--contact">
         <p>Marek Nowak <strong>ZTI 2020/2021</strong> </p>
       </div>
       <div id="contacts--social">
         <p><b-link id="mylink" v-b-modal.RegisterModal>Conditions of Use</b-link></p>
       </div>
     </div>
     <div id="mentions">
       <p>WfiIS AGH 2020/2021. All rights reserved.</p>
     </div>
    </div>
  </div>
</template>


<script>
// import axios from 'axios';
export default {
    name: "Footer",
    data(){
      return {
      regulations: "<p><strong>Regulations of the library portal</strong></p><p><strong>This document specifies the terms of using the library portal.</strong></p>",
      }
    },
};
</script>


<style scoped>
@import url('https://fonts.googleapis.com/css?family=Open+Sans');

body{
  display:flex;
  background-color:#F0F8FF;
  height:95vh;
  justify-content:center;
  align-items:flex-end;
  font-family:Open Sans;
}

#container2{
  border-radius: 10px;
  padding-top:2%;
  margin-bottom:0%;
  margin-top:15%;
  /* width:100vw; */
  color:white;
  background-color:	#6c757d;
  display:flex;
  flex-direction:column;
  align-items:center;
}
#contacts{
  display:flex;
  justify-content:space-around;
  align-items:center;
  width:100%;
}
#contacts--contact{
  text-align:right;
}
#contacts--social{
  width:20%;
  display : flex;
  justify-content : space-between;
}
#contacts--social div{
  height:30px;
  width:30px;
  background-color:white;
  border-radius:50%;
}
#mentions p {
  font-size:0.7em;
}
#mylink{
  color: white;
}
</style>
