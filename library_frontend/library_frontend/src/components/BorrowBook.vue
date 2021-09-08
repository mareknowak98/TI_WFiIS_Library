<template>
  <div class="container">
  <navbar></navbar>
  <b-jumbotron class="jumbotron jumbotron-home">
    <h2>Wybierz użytkownika</h2>

    <label class="typo__label"></label>
    <multiselect v-model="selected_user" deselect-label="Can't remove this value" track-by="id" label="email" placeholder="Wypożyczający" :options="users" :searchable="false" :allow-empty="false">
        <template slot="singleLabel" slot-scope="{ option }">Uzytkownik: <strong>{{ option.email }}</strong></template>
    </multiselect>

    <h2>Wybierz ksiazke</h2>
    <label class="typo__label"></label>
    <multiselect v-model="selected_book" deselect-label="Can't remove this value" track-by="id" label="name" placeholder="Książka" :options="books" :searchable="false" :allow-empty="false">
        <template slot="singleLabel" slot-scope="{ option }">Książka: <strong>{{ option.name }}</strong></template>
    </multiselect>

    <b-button block type="submit" v-on:click="borrowBook()" variant="secondary">Wypożycz</b-button>


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
        borrowed: null
      }
    },

    mounted: function () {
        this.getUsers();
        this.getBooks();
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
        getBooks(){
            let config = {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + localStorage.getItem('user-token')
                }  
            }
            axios.get('http://localhost:5000/books', config)
            .then(res => (this.books = res.data))
            .catch(err => {
            console.log(err);
            })
        },
        borrowBook(){
            let config = {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('user-token')
            }
            }
            axios.post('http://localhost:5000/api/users/addBorrowedToUser/' + this.selected_user.id + '/' + this.selected_book.id + '/add',{
                returned: false,
                startDate: this.today
            }, config)
            .then(res => {
                this.borrowed = res;
                this.selected_book = null;
                this.selected_user = null;
                })
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
