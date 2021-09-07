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
    <div v-if="$getToken() == null">
    <b-jumbotron class="jumbotron jumbotron-special" header=" ">
        <h1> </h1>
        <h1> </h1>
        <h1> </h1>
        <h1> </h1>
    </b-jumbotron>
    <b-navbar toggleable="lg" type="dark" variant="dark">
    

      <b-navbar-brand v-on:click="$goToAnotherPage('/')">Strona główna</b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <b-navbar-nav>
            <b-nav-item v-b-modal.loginModal>Login</b-nav-item>
            <b-nav-item href="/register">Zarejestruj się</b-nav-item>
          </b-navbar-nav>
        </b-navbar-nav>
      </b-collapse>

  </b-navbar>
  </div>
    <div v-else>
      <b-jumbotron class="jumbotron jumbotron-special" header=" ">
          <h1> </h1>
          <h1> </h1>
          <h1> </h1>
          <h1> </h1>
      </b-jumbotron>
      <b-navbar toggleable="lg" type="dark" variant="dark">

        <b-navbar-brand v-on:click="$goToAnotherPage('/')">Strona główna</b-navbar-brand>

        <div v-if="userInfo.roles[0].name == 'ROLE_USER'">
          <b-navbar-nav>
            <b-nav-item href="#">Zarezerwuj książkę</b-nav-item>

            <b-nav-item-dropdown text="Moje konto" right>
              <b-dropdown-item href="#">Wypożyczone książki</b-dropdown-item>
              <b-dropdown-item href="#">Zarezerwowane książki</b-dropdown-item>
              <b-dropdown-item href="#">Oddane ksiażki</b-dropdown-item>
            </b-nav-item-dropdown>
          </b-navbar-nav>
        </div>

        <div v-if="userInfo.roles[0].name == 'ROLE_ADMIN'">
          <b-navbar-nav>
            <b-nav-item href="#">Wypożycz książkę</b-nav-item>
            <b-nav-item href="#">Zwróć książkę</b-nav-item>

            <b-nav-item-dropdown text="Dodaj" right>
              <b-dropdown-item href="/addAuthor">Zarządzaj autorami</b-dropdown-item>
              <b-dropdown-item href="/addCategory">Zarządzaj kategoriami</b-dropdown-item>
              <b-dropdown-item href="/addBook">Dodaj książkę</b-dropdown-item>
            </b-nav-item-dropdown>
          </b-navbar-nav>
        </div>
        
        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-collapse id="nav-collapse" is-nav>

          <!-- Right aligned nav items -->
          <b-navbar-nav class="ml-auto">
            <b-navbar-nav>
              <b-nav-item v-on:click="logout()">Wyloguj się</b-nav-item>
            </b-navbar-nav>
          </b-navbar-nav>
        </b-collapse>

      </b-navbar>
    </div>

  <b-modal id="loginModal" title="Log in" @ok="login" ok-title="Log in">

    <label class="mb-2">Please enter your email and password, then press Log in.</label>
    <b-form-input class="mb-2" placeholder="Email" v-model="email" autocomplete="email"></b-form-input>
    <b-form-input class="mb-2" placeholder="Password" v-model="password" type="password"></b-form-input>
  </b-modal>
  </div>
</template>



<script>
import axios from 'axios';
  export default {
    name: "Navbar",
    components:{

    },
    data(){
      return{
        email: '',
        password: '',
        token: '',
        userInfo: ''
      }
    },
    mounted: function (){
      this.getUserInfo();
    },
    methods: {
        login(){
        axios.post('http://localhost:5000/api/auth/signin',{
          email: this.email,
          password: this.password,
        })
        .then(resp => {
          this.token = resp.data.accessToken; //TODO delete
          localStorage.removeItem('user-token');
          localStorage.setItem('user-token',resp.data.accessToken)
          this.email = '';
          this.password = '';
        })
        .then(resp => {
          console.log(resp)
          this.getUserInfoOnLogin()
        })
        .catch(err => {
          this.email = '';
          this.password = '';
          console.log(err)
          localStorage.removeItem('user-token');
          this.showCredentialsAlert = true;
          this.$forceUpdate();
        })
      },

      logout() {
        localStorage.removeItem('user-token');
        this.$goToMainPage();
        this.$forceUpdate();
      },

      getUserInfo(){
        console.log(localStorage.getItem('user-token'))
        if(localStorage.getItem('user-token') != null){
          let config = {
              headers: {
              'Content-Type': 'application/json',
              'Authorization': 'Bearer ' + localStorage.getItem('user-token')
              }
          }
          axios.get('http://localhost:5000/api/users/me', config)
          .then(res => (this.userInfo = res.data))
          .catch(err => {
          console.log(err);
          })    
        }
      }, 
      getUserInfoOnLogin(){
        console.log(localStorage.getItem('user-token'))
        let config = {
            headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('user-token')
            }
        }
        axios.get('http://localhost:5000/api/users/me', config)
        .then(res => (this.userInfo = res.data))
        .catch(err => {
        console.log(err);
        })    
      }
    
    }
  }
</script>

<style scoped>
.jumbotron-special{
  background-image: url("../../static/books.jpeg");
  background-size: cover;
  margin-bottom: 0.5%;

  padding-bottom: 1%;
}
.jumbotron h1{
  text-align: center;
  padding: 1%;
}

</style>
