<template>
  <div class="container">
  <navbar></navbar>
  <b-jumbotron class="jumbotron jumbotron-home">
    <h2>Wybierz książkę którą chcesz zarezerwować (rezerwacja trwa dwa tygodnie)</h2>

    <label class="typo__label"></label>
    <multiselect v-model="selected_book" deselect-label="Can't remove this value" track-by="id" label="name" placeholder="Książka" :options="books" :searchable="false" :allow-empty="false">
        <template slot="singleLabel" slot-scope="{ option }">Książka: <strong>{{ option.name }}</strong></template>
    </multiselect>
    <b-button block type="submit" v-on:click="reserveBook()" variant="secondary">Zarezerwuj</b-button>


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
    name: "reserveBook",
    components:{
        Navbar,
        Footer,
        Multiselect
    },
  

    data() {
      return {
        dueDate: null,
        books: [],
        selected_book: null
      }
    },

    mounted: function () {
        this.getBooks();
        this.getDatePlusTwoWeeks();
    },

    computed: {
      
    },

    methods:{
        getDatePlusTwoWeeks(){
            var nowDate = new Date(); 
            nowDate.setDate(nowDate.getDate() + 14);
            var date = nowDate.toISOString().split('T')[0];
            this.dueDate=date;
        },
        getBooks(){
        let config = {
            headers: {
            'Content-Type': 'application/json',
            }
        }
            axios.get('http://ec2-3-67-44-255.eu-central-1.compute.amazonaws.com:5000/books', config)
            .then(res => (this.books = res.data))
            .catch(err => {
            console.log(err);
            })
        },
        reserveBook(){
        let config = {
            headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('user-token')
            }
        }
            axios.post('http://ec2-3-67-44-255.eu-central-1.compute.amazonaws.com:5000/reserve/addReservation/' + this.selected_book.id, { dueDate: this.dueDate }, config)
            .then(res => (console.log(res.data),this.selected_book = null))
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
