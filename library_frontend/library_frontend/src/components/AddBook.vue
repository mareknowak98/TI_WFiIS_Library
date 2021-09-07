<template>
  <div class="container">
  <navbar></navbar>
  <b-jumbotron class="jumbotron jumbotron-home">

    <h1>Dodaj książkę:</h1>
      <b-form @submit.prevent="addBook">
        <b-form-group id="input-group-1" label="Tytuł:" label-for="input-1">
        <b-form-input
          id="input-1"
          v-model="form.name"
          required
          placeholder="Tytuł:"
        ></b-form-input>
        </b-form-group>

        <div v-if="authors != null">
        <div>
          <label class="typo__label">Wybierz autorów</label>
          <multiselect v-model="selected_author" :options="authors" :multiple="true" :close-on-select="false" :clear-on-select="false" placeholder="Wybierz autorów" label="author" track-by="id" :preselect-first="false">
            <template slot="selection" slot-scope="{ values, isOpen }"><span class="multiselect__single" v-if="values.length &amp;&amp; !isOpen">{{ values.length }} autorów</span></template>
          </multiselect>
        </div>
        </div>

        <b-form-group id="input-group-2" label="Wydawnictwo:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.publisher"
          required
          placeholder="Wydawnictwo"
        ></b-form-input>
        </b-form-group>

        <div v-if="categories != null">
        <div>
          <label class="typo__label">Wybierz kategorie</label>
          <multiselect v-model="selected_categories" :options="categories" :multiple="true" :close-on-select="false" :clear-on-select="false" placeholder="Wybierz kategorie" label="category" track-by="id" :preselect-first="false">
            <template slot="selection" slot-scope="{ values, isOpen }"><span class="multiselect__single" v-if="values.length &amp;&amp; !isOpen">{{ values.length }} kategorie</span></template>
          </multiselect>
        </div>
        </div>

        <b-form-group id="input-group-3" label="Strony:" label-for="input-3">
        <b-form-input
          id="input-3"
          v-model="form.pages"
          required
          placeholder="Strony"
        ></b-form-input>
        </b-form-group>

        <b-form-group id="input-group-4" label="Nr ISBN:" label-for="input-4">
        <b-form-input
          id="input-4"
          v-model="form.isbn"
          required
          placeholder="ISBN"
        ></b-form-input>
        </b-form-group>

        <b-form-group id="input-group-5" label="Opis:" label-for="input-5">
        <b-form-textarea
          id="textarea-state"
          v-model="form.description"
          :state="form.description.length >= 10"
          placeholder="Opis musi być dłuższy niż 10 znaków"
          rows="3"
        ></b-form-textarea>
        </b-form-group>



      <b-button block type="submit" variant="secondary">Submit</b-button>
      </b-form>
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
    name: "addBook",
    components:{
        Navbar,
        Footer,
        Multiselect,
    },
  

    data() {
      return {
        form: {
          name: '',
          publisher: '',
          pages: '',
          isbn: '',
          description: '',
          authorId: {},
          categoryId: {}
        },
        authors: null,
        categories: null,
        book: '',
        selected_author: null,
        selected_categories: null
      }
    },

    mounted() {
        this.getAuthors();
        this.getCategories();
    },

    computed: {
      
    },

    methods:{

      //TODO fix it
    addBook(){
        axios({
            url: 'http://localhost:5000/books',
            method: 'post',
            headers: {
                'Content-Type': 'application/json', 
                'Authorization': 'Bearer ' + localStorage.getItem('user-token')
                },
            data: {
                name: this.form.name
            }
        }).then(resp => (this.book = resp.data.id ,
        axios({
            url: 'http://localhost:5000/books/' + resp.data.id + '/authors/' + this.form.authorId + '/add',
            method: 'post',
            headers: {
                'Content-Type': 'application/json', 
                },
        })))

      },

      getAuthors(){
         let config = {
          headers: {
            'Content-Type': 'application/json',
          }
        }
          axios.get('http://localhost:5000/authors', config)
          .then(res => (this.authors = res.data))
          .catch(err => {
          console.log(err);
          })
      },

      getCategories(){
         let config = {
          headers: {
            'Content-Type': 'application/json',
          }
        }
          axios.get('http://localhost:5000/categories', config)
          .then(res => (this.categories = res.data))
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

<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>