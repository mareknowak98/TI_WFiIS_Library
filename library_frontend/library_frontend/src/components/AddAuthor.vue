<template>
  <div class="container">
  <navbar></navbar>
  <b-jumbotron class="jumbotron jumbotron-home">

    <b-button-group>
      <b-button v-b-toggle.collapse-1 variant="outline-primary">
        <b-icon icon="plus-circle"></b-icon> Dodaj
      </b-button>
      <b-button v-b-toggle.collapse-2 variant="outline-primary" v-on:click="getAuthors()">
        <b-icon icon="x-circle"></b-icon> Usuń
      </b-button>
    </b-button-group>

    <b-collapse id="collapse-1" class="mt-2">
        <h1>Nowy autor:</h1>
        <b-form>
        <b-form-group id="input-group-1" label="" label-for="input-1">
            <b-form-input
            id="input-1"
            v-model="form.author"
            required
            placeholder="Imię i nazwysko autora"
            ></b-form-input>
        </b-form-group>

        <b-button block type="submit" v-on:click="addAuthor()" variant="secondary">Dodaj</b-button>
        </b-form>
    </b-collapse>

    <b-collapse id="collapse-2" class="mt-2">
        <h1>Usuń autora</h1>
            <label class="typo__label"></label>
            <multiselect v-model="selected_author" deselect-label="Can't remove this value" track-by="author" label="author" placeholder="Wybierz autora do usunięcia" :options="authors" :searchable="false" :allow-empty="false">
                <template slot="singleLabel" slot-scope="{ option }">Autor do usunięcia: <strong>{{ option.author }}</strong></template>
            </multiselect>
        <b-button block type="submit" v-on:click="removeAuthor()" variant="secondary">Usuń</b-button>
    </b-collapse>

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
    name: "addCategory",
    components:{
        Navbar,
        Footer,
        Multiselect
    },
  

    data() {
      return {
        form: {
          author: '',
        },
        authors: '',
        selected_author: null
      }
    },

    mounted: function () {
    },

    computed: {
      
    },

    methods:{
      addAuthor(){
        let config = {
          headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('user-token')
          }
        }
          axios.post('http://ec2-3-67-44-255.eu-central-1.compute.amazonaws.com:5000/authors',{
          author: this.form.author,
          }, config)
          .then(this.form.author = '')
          .catch(err => {
          console.log(err);
          })
      },

      removeAuthor(){
        let config = {
          headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('user-token')
          }
        }
          axios.delete('http://ec2-3-67-44-255.eu-central-1.compute.amazonaws.com:5000/authors/' + this.selected_author.id, config)
          .then(this.selected_author = null, this.getAuthors())
          .catch(err => {
          console.log(err);
          })
      },

      getAuthors(){
         let config = {
          headers: {
            'Content-Type': 'application/json',
          }
        }
          axios.get('http://ec2-3-67-44-255.eu-central-1.compute.amazonaws.com:5000/authors', config)
          .then(res => (this.authors = res.data))
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
