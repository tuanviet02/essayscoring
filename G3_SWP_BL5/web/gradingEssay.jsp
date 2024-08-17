<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css' rel='stylesheet'>
        <title>Form Reviews</title>
        <link rel="stylesheet" href="./style/createEvaluatorEssay.css">
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
        <aside class="sidenav navbar navbar-vertical navbar-expand-xs border-0 border-radius-xl my-3 fixed-start ms-3 bg-gradient-dark"id="sidenav-main" >
      <div class="sidenav-header">
        <i class="fas fa-times p-3 cursor-pointer text-white opacity-5 position-absolute end-0 top-0 d-none d-xl-none" aria-hidden="true" id="iconSidenav"></i>
        <a class="navbar-brand m-0" href="./" target="_blank">
          <img src="./assets/img/logo-ct.png" class="navbar-brand-img h-100" alt="main_logo"/>
          <span class="ms-1 font-weight-bold text-white">Grading</span>
        </a>
      </div>
      <hr class="horizontal light mt-0 mb-2" />
    <%@include file="evaluator/sidebarEvaluator.jsp" %>
    </aside>
        <div class="panel panel-default section-contents-prompt">
            <header class="panel-heading">
                <div class="panel-heading-main">
                    <h3> ${writerEssay.essay.title}</h3>
                    <div class="panel-heading-actions">
                        <a class="start-over saved"><b>Level: </b><i class="icon-start-again"></i></a>
                        <a class="save saved" id="save"><i class="icon-save"></i><span>  ${writerEssay.essay.level.levelName}</span></a></br>
                        <a class="start-over saved"><b>Type: </b><i class="icon-start-again"></i></a>
                        <a class="save saved" id="save"><i class="icon-save"></i><span>  ${writerEssay.essay.type.typeName}</span></a>
                    </div>
                </div>
                <div class="panel-heading-utility"></div>
            </header>
            <main class="panel-body">
                <div class="content">
                    <div>
                        <p>Description: ${writerEssay.essay.description}</p>
                    </div>
                </div>
            </main>
            <div class="section-contents-footer">

            </div>
            <c:set var="evaluatorEssayID" value="${evaluatorEssayID}" />
            <div class="section-input-field">
                <main class="panel-body">
                    <textarea maxlength="9999" placeholder="Write your text here." data-question-id="7e3b5830-dd8e-11e6-9c3d-0600f3293cb7" rows="10" id="script-text" spellcheck="false" data-listener-added_f7595f09="true"  readonly> ${writerEssay.contentEssay}</textarea>
                </main>
            </div>
            <div class="respond start-writing">
                <span>(the word count for this task is about 30 words).</span>
            </div>
            <div class="section-input-bottom"></div>
            <div>
            </div>
        </div>
        <div class="wrapper">

            <h2 style="margin-top: 20px">Rate and review this article.</h2>
            <form action="grading" method="post">

                <div class="rating">

                    <label class="form-label">Grade: </label> <input type="number" name="score" pattern="[0-9]{2}" step="0.01">
                </div>
                <label class="rating" class="form-label">Feedback:</label>
                <textarea name="feedbackContent" cols="30" rows="5" placeholder="Your opinion..." required></textarea>

                <input type="hidden" name="writerEssayId" value="${writerEssay.writerEsssayId}">
                <c:if test="${messSuccess != null}">
                    <div >
                        <h3 class=" alert-success" role="alert">${messSuccess}</h1>
                    </div>
                </c:if>
                <c:if test="${messError != null}">
                    <div>
                        <h3  class=" alert-danger text-success" role="alert">${messError}</h1>
                    </div>
                </c:if>
                <div class="btn-group">
                    <button type="submit" class="btn submit">Submit</button>
                    <button type="button" class="btn cancel" onclick="goBack()">Cancel</button>

                    <button onclick="location.href = 'topicCriteria?writerEssayId=${writerEssay.writerEsssayId}&evaluator_essay_id=${evaluatorEssayID}'"  class="btn">Add Feedback criteria</button>
                </div>
            </form>
        </div>
        <script>
            function goBack() {
                window.history.back();
            }
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

<!--  <h1>Writer Essay Detail</h1>
    <c:if test="${not empty writerEssay}">
        <p>Writer Essay ID: ${writerEssay.writerEsssayId}</p>
        <p>Writer ID: ${writerEssay.writer.userID}</p>
        <p>Essay ID: ${writerEssay.essay.topicId}</p>
        <p>Content Essay: ${writerEssay.contentEssay}</p>
        <p>Limit: ${writerEssay.limit}</p>
        <p>Status: ${writerEssay.status}</p>
        <h2>Topic Detail</h2>
    <p>Topic ID: ${writerEssay.essay.topicId}</p>
    <p>Title: ${writerEssay.essay.title}</p>
    <p>Created Date: ${writerEssay.essay.createdDate}</p>
    <p>Updated Date: ${writerEssay.essay.updatedDate}</p>
    <p>Description: ${writerEssay.essay.description}</p>
    <p>Level ID: ${writerEssay.essay.level.levelId}</p>
    <p>Level Name: ${writerEssay.essay.level.levelName}</p>
    <p>Type ID: ${writerEssay.essay.type.typeId}</p>
    <p>Type Name: ${writerEssay.essay.type.typeName}</p>
    <p>Status: ${writerEssay.essay.status}</p>
    </c:if>
-->