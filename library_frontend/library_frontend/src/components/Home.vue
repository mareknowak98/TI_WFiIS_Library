<template>
  <div class="container">
    <navbar></navbar>
    <b-jumbotron class="jumbotron jumbotron-home">
      <h2>Books</h2>
      <!-- {{books[0]}} -->
      <div class="">

        <b-list-group v-for="(book) in books" :key="book.id">
        <b-list-group-item :to="'/book/' + book.id">

           <b-card no-body class="overflow-hidden">
            <b-row no-gutters>
              <tr>
                <td width="300px">
                  <b-card-img src="https://picsum.photos/600/300/?image=25" fluid alt="Responsive image" style="max-width: 240px"></b-card-img>
              </td>
              </tr>
              <b-col>
                <b-card-body :title="book.name">
                  <b-card-text>
                    <div id="entity-list">
                      <td id="mytext" v-html="book.id"></td>
                    </div>
                  </b-card-text>

                </b-card-body>
              </b-col>
            </b-row>
          </b-card>
        </b-list-group-item>
      </b-list-group>
      </div>

       <Footer></Footer>

     </b-jumbotron>
  </div>
</template>



<script>
import Navbar from '../components/Navbar.vue';
import Footer from '../components/Footer.vue';
import axios from 'axios';
export default {
  name: "Home",
  components: {
    Navbar,
    Footer,
  },
  data() {
    return {
      books: {},
      form: {

      },
    }
  },
  mounted() {
    this.getBooks();
  },



  methods:{
    getBooks(){
      let config = {
        headers: {
          'Content-Type': 'application/json',
        }
      }
        axios.get('http://localhost:5000/books', config)
        .then(res => (this.books = res.data))
        .catch(err => {
        console.log(err);
        })
    }
  }
};
</script>



<style scoped>
@import '../../styles/style.css';

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
