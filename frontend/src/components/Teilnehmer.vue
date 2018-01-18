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

       <table v-if="allusers && allusers.length" id="myTable">
       <tr>
         <th v-on:click="sortTable(0)" class="clickable">Status</th>
         <th v-on:click="sortTable(1)" class="clickable">Name</th>
         <th v-on:click="sortDate()" class="clickable">Buchung</th>
         <th>Addresse</th>
         <th>Telefon</th>
         <th>Bearbeiten</th>
       </tr>
       <tr v-for="alluser of allusers">
         <td :id="alluser.hasPayed">{{alluser.hasPayed}}</td>
         <td>{{alluser.lastName}}, {{alluser.firstName}}</td>
         <td>{{alluser.birthDate}}</td>
         <td>{{alluser.street}}, {{alluser.city}}</td>
         <td>{{alluser.telephone}}</th>

         <td><span v-on:click="kill($event)" class="fakebutton"><a>stornieren</a></span>
           <router-link :to="{path: '../TeilnehmerEdit', query: {id: alluser.id }}" class="fakebutton">Bearbeiten</router-link>
           <span class="fakebutton"><a>als PDF exportieren</a></span>
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
    sortTable (n) {
      var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount;
      switchcount = 0;
      table = document.getElementById('myTable');
      switching = true;
      dir = 'asc';
      while (switching) {
        switching = false;
        rows = table.getElementsByTagName('TR');

        for (i = 1; i < (rows.length - 1); i++) {
          shouldSwitch = false;
          x = rows[i].getElementsByTagName('TD')[n];
          y = rows[i + 1].getElementsByTagName('TD')[n];

          if (dir === 'asc') {
            if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
              shouldSwitch = true;
              break;
            }
          } else if (dir === 'desc') {
            if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
              shouldSwitch = true;
              break;
            }
          }
        }
        if (shouldSwitch) {
          rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
          switching = true;
          switchcount++;
        } else {
          if (switchcount === 0 && dir === 'asc') {
            dir = 'desc';
            switching = true;
          }
        }
      }
    },
    sortDate () {
      var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount;
      switchcount = 0;
      table = document.getElementById('myTable');
      switching = true;
      dir = 'asc';
      while (switching) {
        switching = false;
        rows = table.getElementsByTagName('TR');

        for (i = 1; i < (rows.length - 1); i++) {
          shouldSwitch = false;

          var tmpx = rows[i].getElementsByTagName('TD')[2].innerHTML;
          x = tmpx.toString();
          var patternx = /(\d{2})\.(\d{2})\.(\d{4})/;
          var dx = new Date(x.replace(patternx, '$3-$2-$1'));

          var tmpy = rows[i + 1].getElementsByTagName('TD')[2].innerHTML;
          y = tmpy.toString();
          var patterny = /(\d{2})\.(\d{2})\.(\d{4})/;
          var dy = new Date(y.replace(patterny, '$3-$2-$1'));

          if (dir === 'asc') {
            if (dx > dy) {
              shouldSwitch = true;
              break;
            }
          } else if (dir === 'desc') {
            if (dx < dy) {
              shouldSwitch = true;
              break;
            }
          }
        }
        if (shouldSwitch) {
          rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
          switching = true;
          switchcount++;
        } else {
          if (switchcount === 0 && dir === 'asc') {
            dir = 'desc';
            switching = true;
          }
        }
      }
    },
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
