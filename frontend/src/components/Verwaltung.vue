<template>
	<div>
    <h1>Veranstaltungen verwalten</h1>
    <br/>
    <input type="text" name="label" placeholder="Suchen"> <br/>
    <br/>
    <form v-if="allprojects && allprojects.length" class="form">
      <br/>
        <table id='myTable'>
        <tr>
          <th v-on:click="sortTable(0)">Veranstaltung</th>
          <th v-on:click="sortTable(1)">Datum</th>
          <th> belegt / gesamt Plätze</th>
          <th>Bearbeiten</th>

          <br/>
        </tr>
          <tr v-for="allproject of allprojects">
            <!--<td v-on:click="teil($event)">{{allproject.name}}</td>-->
            <td>{{allproject.name}}</td>
            <td>{{allproject.date}}</td>
            <td>belegt / </nobr> {{allproject.slots}}</td>
            <td><nobr><button v-on:click="kill($event)">löschen</button>
              <button>bearbeiten</button>
              <button>PDF exportieren</button></nobr>
            </td>
          </tr>
      </table>
    </form>

    <!-- The Modal -->
    <div id="delete" class="modal">

      <!-- Modal content -->
      <div class="modal-content">
        <p>Sind sie sicher das Sie die Veranstaltung löschen wollen?</p>
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

