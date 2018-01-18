<template>
	<html>
      <nav>
        <input type="text" class="searchbar" placeholder="Suche ...">
        <a href="/#/Verwaltung/" class="selected">Alle Veranstaltungen</a>
        <a href="/#/Veranstaltung/" >Veranstaltung erstellen </a>
        <a href="/#/Teilnehmer/" >Alle Teilnehmer</a>
        <a href="/#/TeilnehmerAdd/" >Teilnehmer erstellen</a>
        <a href="/#/Reservierung/" >Reservierungen</a>
      </nav>
      <main>
        <h1>Veranstaltungsübersicht</h1>
              <table v-if="allprojects && allprojects.length" id="myTable">
                <tr>
                  <th v-on:click="sortTable(0)" class="clickable">Veranstaltung</th>
                  <th v-on:click="sortDate()" class="clickable">Datum</th>
                  <th> belegt / gesamt Plätze</th>
                  <th>Bearbeiten</th>
                 </tr>
                 <tr v-for="allproject of allprojects">
                 <!--<td v-on:click="teil($event)">{{allproject.name}}</td>-->
                   <td>{{allproject.name}}</td>
                   <td>{{allproject.date}}</td>
                   <td>{{allproject.slotsFree}} / </nobr> {{allproject.slots}}</td>
                   <td><nobr><button v-on:click="kill($event)">löschen</button>
                     <router-link :to="{path: '../VeranstaltungEdit', query: {id: allproject.project_id }}">Bearbeiten</router-link>
                     <button>PDF exportieren</button></nobr>
                   </td>
                 </tr>
               </table>
      </main>
    <!-- The Modal -->
    <div id="delete" class="modal">

      <!-- Modal content -->
      <div class="modal-content">
        <h4>Sind sie sicher das Sie die Veranstaltung löschen wollen?</h4>
        <div class="center"><button>Bestätigen</button><button v-on:click="closeModal()">Abbrechen</button></div>
      </div>

    </div>


	</html>
</template>

<script>
import axios from 'axios';

export default {
  name: 'Verwaltung',
  data () {
    return {
      allprojects: [],
      errors: []
    };
  },
  created () {
    axios.get('http://localhost:8088/api/allprojects')
    .then(response => {
      this.allprojects = response.data
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

          var tmpx = rows[i].getElementsByTagName('TD')[1].innerHTML;
          x = tmpx.toString();
          var patternx = /(\d{2})\.(\d{2})\.(\d{4})/;
          var dx = new Date(x.replace(patternx, '$3-$2-$1'));

          var tmpy = rows[i + 1].getElementsByTagName('TD')[1].innerHTML;
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
    /* teil (event) {
      var span = document.getElementsByClassName('close')[0];
      event.target.parentElement.insertAdjacentHTML('afterend', '</table> <table><tr><th>Thorsten Koenig</th><th><button onclick="this.parentElement.parentElement.remove()">stornieren</button><th><button>als PDF exportieren</button></th></tr> </table>');
      event.target.parentElement.insertAdjacentHTML('afterend', '</table> <table><tr><th>Marie Kohler</th><th><button onclick="this.parentElement.parentElement.remove()">stornieren</button><th><button>als PDF exportieren</button></th></tr> </table>');
      event.target.parentElement.insertAdjacentHTML('afterend', '</table> <table><tr><th>Florian Keller</th><th><button onclick="this.parentElement.parentElement.remove()">stornieren</button><th><button>als PDF exportieren</button></th></tr> </table>');
    }, */
    closeModal () {
      var modal = document.getElementById('delete');
      modal.style.display = 'none';
    }
  }

}
</script>
<!--<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>-->


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.button {
display: inline-block;
float: left;
}
</style>

