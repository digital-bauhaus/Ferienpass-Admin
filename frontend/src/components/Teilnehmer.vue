<template>
  <html>
    <nav>
      <input type="text" class="searchbar" placeholder="Suche ...">
      <a href="/#/Verwaltung/" >Alle Veranstaltungen</a>
      <a href="/#/Veranstaltung/" >Veranstaltung erstellen </a>
      <a href="/#/Teilnehmer/"  class="selected">Alle Teilnehmer</a>
      <a href="/#/TeilnehmerAdd/" >Teilnehmer erstellen</a>
      <a href="/#/Reservierung/" >Reservierungen</a>
    </nav>
    <main>
    <h1>Teilnehmerübersicht</h1>

       <table v-if="allusers && allusers.length">
       <tr>
         <th>Status</th>
         <th>Name</th>
         <th>Addresse</th>
         <th>Telefon</th>
         <th>Bearbeiten</th>
       </tr>
       <tr v-for="alluser of allusers">
         <td :id="alluser.hasPayed">{{alluser.hasPayed}}</td>
         <td>{{alluser.lastName}}, {{alluser.firstName}}</td>
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
        <h4>Sind sie sicher das Sie den Teilnehmer stornieren wollen?</h4>
        <div class="center"><button>Bestätigen</button><button v-on:click="closeModal()">Abbrechen</button></div>
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

td:nth-child(1) {
    background-color: #e5e22b;
}
#true {
    background-color: #5ed15e;
}


</style>
