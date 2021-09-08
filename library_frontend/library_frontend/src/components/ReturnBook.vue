<template>
  <div class="container">
  <navbar></navbar>
  <b-jumbotron class="jumbotron jumbotron-home">
    <h2>Wybierz użytkownika</h2>

    <label class="typo__label"></label>
    <multiselect v-model="selected_user" deselect-label="Can't remove this value" track-by="id" label="email" placeholder="Wypożyczający" :options="users" :searchable="false" :allow-empty="false">
        <template slot="singleLabel" slot-scope="{ option }">Uzytkownik: <strong>{{ option.email }}</strong></template>
    </multiselect>


    <b-button block type="submit" v-on:click="borrowBook()" variant="secondary">Oddaj</b-button>


    <Footer></Footer>
  </b-jumbotron>
  </div>
</template>



<script>
import Navbar from './Navbar.vue'
import Footer from './Footer.vue'
import Multiselect from 'vue-multiselect'


import axios from 'axios';
  export default {
    name: "borrowBook",
    components:{
        Navbar,
        Footer,
        Multiselect
    },
  

    data() {
      return {
        users: [],
        books: [],
        selected_book: null,
        selected_user: null,
        today: null,
        returned: null
      }
    },

    mounted: function () {
        this.getUsers();
        this.getDate();
    },

    computed: {
      
    },

    methods:{
        getDate(){
            var nowDate = new Date(); 
            var date = nowDate.toISOString().split('T')[0];
            this.today=date;
        },
        getUsers(){
            let config = {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + localStorage.getItem('user-token')
                }  
            }
            axios.get('http://localhost:5000/api/users/all', config)
            .then(res => (this.users = res.data))
            .catch(err => {
            console.log(err);
            })
        },


    }
  }
</script>

<style scoped>
@media (min-width: 100px) {
    .container{
        max-width: 1400px;
    }
}

</style>
