/* DOC JS */

new WOW().init();

$(document).ready(function() {
    /*funcao pra esconver a div*/
    $(".conteudo").hide();

    /*funcao pra mostrar a div*/
    $(".retangulo").click(function() {
        $(".conteudo").show();
    });

    /*funcao pra fechar*/
    $(".close").click(function() {
      $(".conteudo").show();
    });

    /*funcao pra fechar*/
    $(".fechar").click(function() {
        $(".conteudo").hide();
    });
});
