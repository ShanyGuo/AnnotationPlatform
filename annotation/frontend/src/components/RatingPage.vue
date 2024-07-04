<template>
  <div class="container">
    <div class="instruction-area">
      <h2>{{stream}} Words Complexity Rating</h2>
      <p>Instructions for using the rating system:</p>
      <ul>
        <li>Rate each item from 1 to 5 stars. 1 star means very easy to understand the keyword in the given sentence, 5 stars means hard to understand the keyword in the given sentence.</li>
        <li>The "Keywords" column indicates the word that needs to be rated, the "Index" column shows the position of the keyword in the sentence, and the "Sentence" column provides the context sentence around the keyword.</li>
        <li>Scroll down and make sure to rate all samples before submitting.</li>
        <li>All samples MUST be rated in one sitting, which takes about 1 hour, without breaks in this process.</li>
        <li>Click the submit button to send your ratings.</li>
      </ul>
    </div>
    <div class="content-area">
      <el-table :data="items" style="width: 100%" height="775" :header-cell-style="{'background-color': '#f5f5f5'}" border>
        <el-table-column prop="keywords" label="Keywords" width="120">
        </el-table-column>
        <el-table-column prop="index" label="Index" width="120">
        </el-table-column>
        <el-table-column prop="sentence" label="Sentence" width="760">
        </el-table-column>
        <el-table-column label="Complexity Rating" width="180">
          <template slot-scope="scope">
            <el-rate v-model="scope.row.rating" :max="5" @change="handleRatingChange(scope.row)"></el-rate>
          </template>
        </el-table-column>
      </el-table>
      <div class="button-container">
        <el-button type="primary" @click="submitRatings">Submit Ratings</el-button>
      </div>
    </div>
  </div>
</template>

<script>
//import axios from 'axios';
import { MessageBox } from 'element-ui';

export default {
  data() {
    return {
      stream: "Mandarin",
      items: [
               {
                 "id": 1,
                 "keywords": "中国",
                 "corpus_category": "people's daily",
                 "index": "6",
                 "sentence": "维拉潘指出，中国是亚足联的重要成员，拥有众多的训练场地、有潜力的球员和执著的球迷。",
                 "rating": -1
               },
               {
                 "id": 1,
                 "keywords": "中国",
                 "corpus_category": "people's daily",
                 "index": "6",
                 "sentence": "维拉潘指出，中国是亚足联的重要成员，拥有众多的训练场地、有潜力的球员和执著的球迷。",
                 "rating": -1
               },
               {
                 "id": 1,
                 "keywords": "中国",
                 "corpus_category": "people's daily",
                 "index": "6",
                 "sentence": "维拉潘指出，中国是亚足联的重要成员，拥有众多的训练场地、有潜力的球员和执著的球迷。",
                 "rating": -1
               },
               {
                 "id": 10,
                 "keywords": "有",
                 "corpus_category": "weibo_covid",
                 "index": "9",
                 "sentence": "勤洗手，少摸脸，还有就是“错误的安全感”。",
                 "rating": -1
               }
             ]
    };
  },
  methods: {
    submitRatings() {
      const invalidItems = this.items.filter(item => item.rating === -1);
        if (invalidItems.length > 0) {
          MessageBox.alert('All items must be rated before submitting.', 'Error', {
            confirmButtonText: 'OK',
            showClose: false,
            type: 'warning'
          });
          return;
        } else {
            console.log(this.items);
            this.$router.push('/thank-you')
        }
    }
  }
};
</script>

<style scoped>

.container {
  display: flex;
  height: 100vh;
}

.instruction-area {
  width: 250px;
  padding: 20px;
  background-color: #f5f5f5;
  overflow-y: auto; /* To allow scrolling if instructions are too long */
  position: fixed; /* Fixes the instruction area */
  height: 100%; /* Full height */
}

.content-area {
  margin-left: 300px; /* Same width as the instruction area + some gap */
  //padding: 20px;
  overflow-y: auto; /* Allows scrolling of the table content */
  position: fixed;
  flex-grow: 1;
}

.button-container {
  display: flex;
  justify-content: center;
  width: 100%;
}

.el-table {
  width: 100%;
}

.el-button {
  margin-top: 20px;
}

</style>
