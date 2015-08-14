
requirejs.config({
  paths: {
    jquery : "../component/jquery/jquery"
  }
});

requirejs(['jquery'], function(jQuery){
  jQuery(document).ready(function() {
    alert("我是谁11111");
  });
});

