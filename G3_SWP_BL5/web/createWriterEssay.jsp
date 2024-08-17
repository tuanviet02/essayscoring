<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Email Description</title>
        <link rel="stylesheet" href="./style/createWriterEssay.css">
        <!--     Fonts and icons     -->
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Roboto+Slab:400,700"/>
        <!-- Nucleo Icons -->
        <link href="./assets/css/nucleo-icons.css" rel="stylesheet" />
        <link href="./assets/css/nucleo-svg.css" rel="stylesheet" />
        <!-- Font Awesome Icons -->
        <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
        <!-- Material Icons -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Round" rel="stylesheet"/>
        <!-- CSS Files -->
        <link id="pagestyle" href="./assets/css/material-dashboard.css?v=3.0.0" rel="stylesheet"/>
    </head>
    <body>
     
        <div class="panel panel-default section-contents-prompt">
            <header class="panel-heading">
                <div class="panel-heading-main">
                    <h3> ${topic.title}</h3>
                    <div class="panel-heading-actions">
                        <a class="start-over saved"><b>Level: </b><i class="icon-start-again"></i></a>
                        <a class="save saved" id="save"><i class="icon-save"></i><span>  ${topic.level.levelName}</span></a></br>
                        <a class="start-over saved"><b>Type: </b><i class="icon-start-again"></i></a>
                        <a class="save saved" id="save"><i class="icon-save"></i><span>  ${topic.type.typeName}</span></a>
                    </div>
                </div>
                <div class="panel-heading-utility"></div>
            </header>
            <main class="panel-body">
                <div class="content">
                    <div>
                        <p>Description: ${topic.description}</p>

                    </div>
                </div>
            </main>
            <div class="section-contents-footer">
                <i>Do not write your real name and surname or email address in your answer.</i>
            </div>


            <form action="createWriterEssay" method="post">
                <input type="hidden" name="essay_id" value="${topic.topicId}"> 
                <div class="section-input-field">
                    <main class="panel-body">
                        <textarea id="content_essay" maxlength="9999" placeholder="Write your text here." data-question-id="7e3b5830-dd8e-11e6-9c3d-0600f3293cb7" rows="10" id="script-text" name="content_essay" spellcheck="false" data-listener-added_f7595f09="true" required></textarea>
                    </main>
                </div>
                <div class="respond start-writing">
                    <span>(the word count for this task is about 30 words).</span>
                </div>
                <c:if test="${messSuccess != null}">
                    <div class=" alert-success" role="alert">
                        <h3>${messSuccess}</h1>
                    </div>
                </c:if>
                <c:if test="${messError != null}">
                    <div class=" alert-danger text-success" role="alert">
                        <h3>${messError}</h1>
                    </div>
                </c:if>
                <div class="btn-group">
                    <button type="submit" class="btn btn-dribbble submit">Submit</button>
                    <button type="button" class="btn btn-dark cancel" onclick="goBack()">Cancel</button>
                </div>
                <div class="container">
                    <h5>Word Counter</h5>

                    <div class="output row">
                        <div>Characters: <span id="characterCount">0</span>.   Words: <span id="wordCount">0</span> </div>.         
                    </div>
                    <div class="output row">
                        <div>Sentences: <span id="sentenceCount">0</span>.   Paragraphs: <span id="paragraphCount">0</span></div>.
                    </div>
                    <!--        <div class="output row">
                                <div>Reading Time: <span id="readingTime">0</span></div>
                                <div id="readability">Show readability score.</div>
                            </div>-->
                    <!--        <div class="keywords">
                                Top keywords:
                                <ul id="topKeywords">
                                </ul>
                            </div>-->
                </div>
            </form> 
        </div>


    
    <script>
        function goBack() {
            window.history.back();
        }
    </script>
    <script>"use strict";
        var input = document.querySelector('#content_essay');
        var characterCount = document.querySelector('#characterCount');
        var wordCount = document.querySelector('#wordCount');
        var sentenceCount = document.querySelector('#sentenceCount');
        var paragraphCount = document.querySelector('#paragraphCount');
//var readingTime = document.querySelector('#readingTime');
//var readability = document.querySelector('#readability');
//var keywordsDiv = document.querySelector('.keywords');
//var topKeywords = document.querySelector('#topKeywords');

        input.addEventListener('keyup', function () {
            var text = input.value.trim();


            characterCount.textContent = text.length;


            var words = text.match(/\b\w+\b/g);
            wordCount.textContent = words ? words.length : 0;


            var sentences = text.split(/[.!?]+/g).filter(Boolean);
            sentenceCount.textContent = sentences.length;


            var paragraphs = text.split(/\n\s*\n/).filter(Boolean);
            paragraphCount.textContent = paragraphs.length;


//    var wordsPerMinute = 275; 
//    var estimatedTime = Math.ceil(words.length / wordsPerMinute);
//    readingTime.textContent = estimatedTime + " minute(s)";


//    var wordCounts = {};
//    words.forEach(function(word) {
//        word = word.toLowerCase();
//        if (wordCounts[word]) {
//            wordCounts[word]++;
//        } else {
//            wordCounts[word] = 1;
//        }
//    });


//    var sortedWords = Object.keys(wordCounts).sort(function(a, b) {
//        return wordCounts[b] - wordCounts[a];
//    });

//    topKeywords.innerHTML = "";
//    for (var i = 0; i < Math.min(4, sortedWords.length); i++) {
//        var li = document.createElement('li');
//        li.textContent = sortedWords[i] + ': ' + wordCounts[sortedWords[i]];
//        topKeywords.appendChild(li);
//    }
        });

    </script>
  <!--   Core JS Files   -->
    <script src="./assets/js/core/popper.min.js"></script>
    <script src="./assets/js/core/bootstrap.min.js"></script>
    <script src="./assets/js/plugins/perfect-scrollbar.min.js"></script>
    <script src="./assets/js/plugins/smooth-scrollbar.min.js"></script>
    <script>
      var win = navigator.platform.indexOf("Win") > -1;
      if (win && document.querySelector("#sidenav-scrollbar")) {
        var options = {
          damping: "0.5",
        };
        Scrollbar.init(document.querySelector("#sidenav-scrollbar"), options);
      }
    </script>
    <!-- Github buttons -->
    <script async defer src="https://buttons.github.io/buttons.js"></script>
    <!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
    <script src="./assets/js/material-dashboard.min.js?v=3.0.0"></script>
</body>
</html>
