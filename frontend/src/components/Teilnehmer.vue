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
       <tr v-for="alluser of allusers" :id ="alluser.hasPayed">
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
    <!-- The Modal -->
    <div id="delete" class="modal">

      <!-- Modal content -->
      <div class="modal-content">
        <p>Sind sie sicher das Sie den Teilnehmer stornieren wollen?</p>
        <button>Bestätigen</button><button v-on:click="closeModal()">Abbrechen</button>
      </div>
    </div>

    <footer>
      <a href="/#/Test/" >Zurück zur Übersicht</a>
    </footer>

  </div>
</template>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
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
      var modal = document.getElementById('delete');
      modal.style.display = 'block';
    /* event.target.parentElement.parentElement.parentElement.remove(); */
    },

    closeModal () {
      var modal = document.getElementById('delete');
      modal.style.display = 'none';
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
    border: 1px solid #000000;
    text-align: left;
    padding: 8px;
    background-color: #efe813;
}

#true > th {
    background-color: #9aeaa0;
}
tr {
    bgcolor: #19ff4b;
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
/* The Modal (background) */
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content/Box */
.modal-content {
    background-color: #fefefe;
    margin: 15% auto; /* 15% from the top and centered */
    padding: 20px;
    border: 1px solid #888;
    width: 80%; /* Could be more or less, depending on screen size */
}
.button {
display: inline-block;
float: left;
}
</style>
