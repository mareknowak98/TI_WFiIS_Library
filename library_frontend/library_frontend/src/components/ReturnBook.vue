<template>
  <div class="container">
  <navbar></navbar>
  <b-jumbotron class="jumbotron jumbotron-home">
    <h2>Wybierz użytkownika</h2>

    <label class="typo__label"></label>
    <multiselect v-model="selected_user" deselect-label="Can't remove this value" track-by="id" label="email" placeholder="Wypożyczający" :options="users" :searchable="false" :allow-empty="false">
        <template slot="singleLabel" slot-scope="{ option }">Uzytkownik: <strong>{{ option.email }}</strong></template>
    </multiselect>

    <b-button block type="submit" v-on:click="getBorrowedByUser()" variant="secondary">Wyszukaj książki wypożyczone przez użytkownika</b-button>

    <b-list-group v-for="(i) in borrowed" :key="i.id">
      <div v-if="i.returned == false">

        <ul class="list-unstyled">
        <b-media tag="li">
          <template #aside>
            <b-img :src="require('../../static/single_book.jpeg')" blank-color="#abc" width="64" alt="placeholder"></b-img>
          </template>
          <h5 class="mt-0 mb-1" style="text-align: left;">{{i.book.name}}</h5>
          <b-row>
            <b-col>Wypożyczono: {{i.startDate}}</b-col>
            <b-col>
              <b-button type="submit" v-on:click="returnBook(i)" variant="secondary">Zwróć</b-button>
            </b-col>
          </b-row>
          <hr>
        </b-media>
        </ul>
      
      </div>
    </b-list-group>

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
    name: "returnBook",
    components:{
        Navbar,
        Footer,
        Multiselect
    },
  

    data() {
      return {
        users: [],
        borrowed: [],
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

        getBorrowedByUser(){
          let config = {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + localStorage.getItem('user-token')
                }  
            }
            axios.get('http://localhost:5000/borrow/getUserBorrowed/' + this.selected_user.id , config)
            .then(res => (this.borrowed = res.data))
            .catch(err => {
            console.log(err);
            })
        },
        returnBook(i){
          console.log(i);
          let config = {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('user-token')
            }
            }
            axios.put('http://localhost:5000/api/users/addBorrowedToUser/return',{
                id: i.id,
                startDate: i.startDate,
                returned: true,
                dueDate: this.today,
            }, config)
            .catch(err => {
            console.log(err);
            })
        }


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
