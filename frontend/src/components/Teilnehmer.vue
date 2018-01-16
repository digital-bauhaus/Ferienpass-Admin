<template>
  <div>
    <h1>Teilnehmer</h1>
    <br/>
    <!--
    <form action="#">
      <p>
        <input type="search" placeholder="Suchen" list="Teilnehmer">
        <datalist id="Teilnehmer">
          <option value="Koenig,Thorsten"></option>
          <option value="Kohler, Marie"></option>
          <option value="Keller, Florian"></option>
          <option value="Wirtz, Felix"></option>
          <option value="Steinbrecher, Jonas"></option>
          <option value="Durr, Tina"></option>
        </datalist>
        <button>finden</button>
      </p>
    </form>-->
    <form v-if="allusers && allusers.length" class="form">
      <br/>
      <table>
       <tr v-for="alluser of allusers">
          <th>{{alluser.lastName}}, {{alluser.firstName}}
          </th>
          <th>{{alluser.birthDate}}</th>
          <th>{{alluser.street}}</th>
          <th>{{alluser.city}}</th>
          <th>{{alluser.telephone}}</th>

          <th><button v-on:click="kill($event)">stornieren</button>
          <!--<button onclick="window.location.href='/#/TeilnehmerEdit?id='+alluser.id">bearbeiten</button>-->
            <router-link :to="{path: '../TeilnehmerEdit', query: {id: alluser.id }}">Bearbeiten</router-link>
            <button>als PDF exportieren</button></th>
        </tr>
      </table>
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
      allusers: [],
      errors: []
    };
  },
  created () {
    axios.get('http://localhost:8088/api/allusers')
    .then(response => {
      this.allusers = response.data
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
footer {
    clear: both;
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
