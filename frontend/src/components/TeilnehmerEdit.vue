<template>
  <div>
    <h1>Teilnehmer bearbeiten</h1>
    <br/>
    <form>
      <input type="text" placeholder="Nachname" :value="user.lastName"> <br/>
      &nbsp <br/>
      <input type="text" placeholder="Vorname" :value="firstName"> <br/>
      &nbsp <br/>
      <input type="text" placeholder="Geburtstag" :value="user.birthDate"><br/>
      &nbsp <br/>
      <input type="text" placeholder="Straße" :value="user.street"><br/>
      &nbsp <br/>
      <input type="text" placeholder="Postleitzahl" :value="user.postcode"><br/>
      &nbsp <br/>
      <input type="text" placeholder="Stadt" :value="user.city"> <br/>
      &nbsp <br/>
      <input type="text" placeholder="Telefonnummer" :value="user.telephone"><br/>
      &nbsp <br/>
      <input type="text" placeholder="Krankenversicherungsnummer" :value="user.healthcareNr"><br/>
      &nbsp <br/>
      <input type="text" placeholder="Behandlung" :value="user.allowTreatment"><br/>
      &nbsp <br/>
      <input type="submit" v-on:click="create()" value="Hinzufügen">
    </form>
    <footer>
      <a href="/#/Test/" >Zurück zur Übersicht</a>
    </footer>

  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'Teilnehmer',
  data () {
    return {
      user: [],
      errors: []
    };
  },
  created () {
    var id = parseFloat(this.$route.query.id);
    axios.get('http://localhost:8088/api/user/' + id)
    .then(response => {
      this.user = response.data
    })
    .catch(e => {
      this.errors.push(e)
    })
  },
  methods: {
    kill (event) {
      event.target.parentElement.parentElement.remove();
    }
  }
}

</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  margin: 0 10px;
}

a {
  color: #42b983;
}

input[type=submit] {
    background-color: #4CAF50; /* Green */
    border: none;
    color: white;
    padding: 10px 24px;
    border-radius: 8px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
}

footer {
    clear: both;
    background: White;
    padding: 0;
    text-align: center;
    vertical-align: middle;
    line-height: normal;
    margin: 0;
    position: fixed;
    bottom: 0px;
    width: 100%;
}
</style>

