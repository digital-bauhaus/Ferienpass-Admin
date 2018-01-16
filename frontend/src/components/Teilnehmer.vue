<template>
  <html>
    <nav>
      <input type="text" class="searchbar" placeholder="Suche ...">
      <a href="/#/Veranstaltung/" >Veranstaltung erstellen</a>
      <a href="/#/Verwaltung/" >Veranstaltungen verwalten</a>
      <a href="/#/Teilnehmer/" >Teilnehmer</a>
      <a href="/#/Reservierung/" >Reservierungen</a>
    </nav>
    <main>
    <h1>Teilnehmerübersicht</h1>

    <!--
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

       <table v-if="allusers && allusers.length">
       <tr>
         <th>Name</th>
         <th>Geburtstag</th>
         <th>Addresse</th>
         <th>Telefon</th>
         <th>Bearbeiten</th>
       </tr>
       <tr v-for="alluser of allusers" :id="alluser.hasPayed">
         <td>{{alluser.lastName}}, {{alluser.firstName}}</td>
         <td>{{alluser.birthDate}}</td>
         <td>{{alluser.street}}, {{alluser.city}}</td>
         <td>{{alluser.telephone}}</th>

         <td><button v-on:click="kill($event)">stornieren</button>
           <router-link :to="{path: '../TeilnehmerEdit', query: {id: alluser.id }}">Bearbeiten</router-link>
           <button>als PDF exportieren</button>
         </td>
       </tr>
     </table>
    </main>
    <!-- The Modal -->
    <div id="delete" class="modal">

      <!-- Modal content -->
      <div class="modal-content">
        <p>Sind sie sicher das Sie den Teilnehmer stornieren wollen?</p>
        <button>Bestätigen</button><button v-on:click="closeModal()">Abbrechen</button>
      </div>
    </div>

  </html>
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

#true {
    background-color: #9aeaa0;
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
