<template>
  <div class="container">
  <navbar></navbar>
  <b-jumbotron class="jumbotron jumbotron-home">

    <b-button-group>
      <b-button v-b-toggle.collapse-1 variant="outline-primary">
        <b-icon icon="plus-circle"></b-icon> Dodaj
      </b-button>
      <b-button v-b-toggle.collapse-2 variant="outline-primary" v-on:click="getCategories()">
        <b-icon icon="x-circle"></b-icon> Usuń
      </b-button>
    </b-button-group>

    <b-collapse id="collapse-1" class="mt-2">
        <h1>Nowa kategoria:</h1>
        <b-form>
        <b-form-group id="input-group-1" label="" label-for="input-1">
            <b-form-input
            id="input-1"
            v-model="form.category"
            required
            placeholder="Kategoria"
            ></b-form-input>
        </b-form-group>

        <b-button block type="submit" v-on:click="addCategory()" variant="secondary">Dodaj</b-button>
        </b-form>
    </b-collapse>

    <b-collapse id="collapse-2" class="mt-2">
        <h1>Usuń kategorię</h1>
            <label class="typo__label"></label>
            <multiselect v-model="selected_category" deselect-label="Can't remove this value" track-by="category" label="category" placeholder="Select one" :options="categories" :searchable="false" :allow-empty="false">
                <template slot="singleLabel" slot-scope="{ option }">Kategoria do usunięcia: <strong>{{ option.category }}</strong></template>
            </multiselect>
        <b-button block type="submit" v-on:click="removeCategory()" variant="secondary">Usuń</b-button>
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
          category: '',
        },
        categories: '',
        selected_category: null
      }
    },

    mounted: function () {
    },

    computed: {
      
    },

    methods:{
      addCategory(){
        let config = {
          headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('user-token')
          }
        }
          axios.post('http://ec2-3-67-44-255.eu-central-1.compute.amazonaws.com:5000/categories',{
          category: this.form.category,
          }, config)
          .then(this.form.category = '')
          .catch(err => {
          console.log(err);
          })
      },

      removeCategory(){
        let config = {
          headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('user-token')
          }
        }
          axios.delete('http://ec2-3-67-44-255.eu-central-1.compute.amazonaws.com:5000/categories/' + this.selected_category.id, config)
          .then(this.selected_category = null, this.getCategories())
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
          axios.get('http://ec2-3-67-44-255.eu-central-1.compute.amazonaws.com:5000/categories', config)
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
