function initWsyiwygDemo0(element){

        $(element).wysiwyg({
            'class': 'fake-bootstrap',
            toolbar: 'top-selection',
            width:"500px",
            buttons: {
                fontname: {
                    title: '字体',
                    image: '\uf031',
                    popup: function( $popup, $button ) {
                            var list_fontnames = {
                                    // Name : Font
                                    'Arial, Helvetica' : 'Arial,Helvetica',
                                    'Verdana'          : 'Verdana,Geneva',
                                    'Georgia'          : 'Georgia',
                                    'Courier New'      : 'Courier New,Courier',
                                    'Times New Roman'  : 'Times New Roman,Times',
                                    '微软雅黑'  : 'Microsoft YaHei',
                                    '宋体'  : 'SimSun',
                                    '黑体'  : 'SimHei',
                                    '楷体'  : 'KaiTi'
                                    
                                };
                            var $list = $('<div/>').addClass('wysiwyg-plugin-list')
                                                   .attr('unselectable','on');
                            $.each( list_fontnames, function( name, font ) {
                                var $link = $('<a/>').attr('href','#')
                                                    .css( 'font-family', font )
                                                    .html( name )
                                                    .click(function(event) {
                                                        $(element).wysiwyg('shell').fontName(font).closePopup();
                                                        // prevent link-href-#
                                                        event.stopPropagation();
                                                        event.preventDefault();
                                                        return false;
                                                    });
                                $list.append( $link );
                            });
                            $popup.append( $list );
                           },
                    showstatic: true,
                    showselection: false
                },
                fontsize: {
                    title: '字体大小',
                    image: '\uf034',
                    popup: function( $popup, $button ) {
                            var list_fontsizes = [];
                            for( var i=8; i <= 11; ++i )
                                list_fontsizes.push(i+'px');
                            for( var i=12; i <= 28; i+=2 )
                                list_fontsizes.push(i+'px');
                            list_fontsizes.push('36px');
                            list_fontsizes.push('48px');
                            list_fontsizes.push('72px');
                            var $list = $('<div/>').addClass('wysiwyg-plugin-list')
                                                   .attr('unselectable','on');
                            $.each( list_fontsizes, function( index, size ) {
                                var $link = $('<a/>').attr('href','#')
                                                    .html( size )
                                                    .click(function(event) {
                                                        $(element).wysiwyg('shell').fontSize(7).closePopup();
                                                        $(element).wysiwyg('container')
                                                                .find('font[size=7]')
                                                                .removeAttr("size")
                                                                .css("font-size", size);
                                                        // prevent link-href-#
                                                        event.stopPropagation();
                                                        event.preventDefault();
                                                        return false;
                                                    });
                                $list.append( $link );
                            });
                            $popup.append( $list );
                           },
                    showstatic: true,
                    showselection: false
                },
                bold: {
                    title: '加粗(Ctrl+B)',
                    image: '\uf032', 
                    hotkey: 'b',
                    showstatic: true,
                    showselection: false
                },
                italic: {
                    title: '斜体(Ctrl+I)',
                    image: '\uf033',
                    hotkey: 'i',
                    showstatic: true,
                    showselection: false
                },
                underline: {
                    title: '下划线(Ctrl+U)',
                    image: '\uf0cd',
                    hotkey: 'u',
                    showstatic: true,
                    showselection: false
                },
                strikethrough: {
                    title: '删除线 (Ctrl+S)',
                    image: '\uf0cc',
                    hotkey: 's',
                    showstatic: true,
                    showselection: false
                },
                forecolor: {
                    title: '文本颜色',
                    image: '\uf1fc',
                    showstatic: true,
                    showselection: false
                },
                highlight: {
                    title: '背景色',
                    image: '\uf043',
                    showstatic: true,
                    showselection: false
                },
                alignleft:{
                    title: '居左',
                    image: '\uf036',
                    showstatic: true,
                    showselection: false    // wanted on selection
                },
                aligncenter:{
                    title: '居中',
                    image: '\uf037',
                    showstatic: true,
                    showselection: false
                },
                alignright:{
                    title: '居右',
                    image: '\uf038',
                    showstatic: true,
                    showselection: false
                },
               alignjustify:{
                    title: 'Justify',
                    image: '\uf039',
                    showstatic: false,
                    showselection: false
                },
                indent:{
                    title: '缩进',
                    image: '\uf03c',
                    showstatic: true,
                    showselection: false
                },
                outdent:{
                    title: '减少缩进',
                    image: '\uf03b',
                    showstatic: true,
                    showselection: false
                },
                orderedList:{
                    title: '有序列表',
                    image: '\uf0cb',
                    showstatic: false,
                    showselection: false
                },
                unorderedList:{
                    title: '无序列表',
                    image: '\uf0ca',
                    showstatic: false,
                    showselection: false
                },
                insertimage: {
                    title: '插入图片',
                    image: '\uf030', // <img src="path/to/image.png" width="16" height="16" alt="" />
                    showstatic: true,    //是否显示在工具栏上
                    showselection:false    //选中时是否显示
                },
                insertlink: {
                    title: '插入链接',
                    image: '\uf08e',
                    showstatic: true,
                    showselection:false
                },
                removeformat: {
                    title: '清除所有样式',
                    image: '\uf12d',
                    showstatic: true,
                    showselection:false
                },
	            submit: {
	                title: '保存',
	                image: '\uf00c',
	                popup: function(){
	                	var parent = $(element).parent().parent().parent();
	                	var classV = $(parent).attr("class");
	                	var id = classV.split("_textFuwenben")[0];
	                	$("#" + id).find(".textComp").html($(element).html());
	                	$(parent).remove();
	                },
	                showstatic: true,
                    showselection: false
	            }
            },
            // Other properties
            selectImage: '添加图片',
            placeholderUrl: 'http://www.example.com',
            placeholderEmbed: '<embed/>',
            maxImageSize: [600,200],
            //filterImageType: callback( file ) {},
            onKeyDown: function( key, character, shiftKey, altKey, ctrlKey, metaKey ) {
                        },
            onKeyPress: function( key, character, shiftKey, altKey, ctrlKey, metaKey ) {
                        },
            onKeyUp: function( key, character, shiftKey, altKey, ctrlKey, metaKey ) {
                     },
            onAutocomplete: function( typed, key, character, shiftKey, altKey, ctrlKey, metaKey ) {
                            if( typed.indexOf('@') == 0 ) // startswith '@'
                            {
                                var usernames = [
                                        'Evelyn',
                                        'Harry',
                                        'Amelia',
                                        'Oliver',
                                        'Isabelle',
                                        'Eddie',
                                        'Editha',
                                        'Jacob',
                                        'Emily',
                                        'George',
                                        'Edison'
                                    ];
                                var $list = $('<div/>').addClass('wysiwyg-plugin-list')
                                                       .attr('unselectable','on');
                                $.each( usernames, function( index, username ) {
                                    if( username.toLowerCase().indexOf(typed.substring(1).toLowerCase()) !== 0 ) // don't count first character '@'
                                        return;
                                    var $link = $('<a/>').attr('href','#')
                                                        .text( username )
                                                        .click(function(event) {
                                                            var url = 'http://example.com/user/' + username,
                                                                html = '<a href="' + url + '">@' + username + '</a> ';
                                                            var editor = $(element).wysiwyg('shell');
                                                            // Expand selection and set inject HTML
                                                            editor.expandSelection( typed.length, 0 ).insertHTML( html );
                                                            editor.closePopup().getElement().focus();
                                                            // prevent link-href-#
                                                            event.stopPropagation();
                                                            event.preventDefault();
                                                            return false;
                                                        });
                                    $list.append( $link );
                                });
                                if( $list.children().length )
                                {
                                    if( key == 13 )
                                    {
                                        $list.children(':first').click();
                                        return false; // swallow enter
                                    }
                                    // Show popup
                                    else if( character || key == 8 )
                                        return $list;
                                }
                            }
                        },
            onImageUpload: function( insert_image ) {
                            var iframe_name = 'legacy-uploader-' + Math.random().toString(36).substring(2);
                            $('<iframe>').attr('name',iframe_name)
                                         .load(function() {
                                            // <iframe> is ready - we will find the URL in the iframe-body
                                            var iframe = this;
                                            var iframedoc = iframe.contentDocument ? iframe.contentDocument :
                                                           (iframe.contentWindow ? iframe.contentWindow.document : iframe.document);
                                            var iframebody = iframedoc.getElementsByTagName('body')[0];
                                            var image_url = iframebody.innerHTML;
                                            insert_image( image_url );
                                            $(iframe).remove();
                                         })
                                         .appendTo(document.body);
                            var $input = $(this);
                            $input.attr('name','upload-filename')
                                  .parents('form')
                                  .attr('action','/script.php') // accessing cross domain <iframes> could be difficult
                                  .attr('method','POST')
                                  .attr('enctype','multipart/form-data')
                                  .attr('target',iframe_name)
                                  .submit();
                        },
            forceImageUpload: false,    // upload images even if File-API is present
            videoFromUrl: function( url ) {
                var youtube_match = url.match( /^(?:http(?:s)?:\/\/)?(?:[a-z0-9.]+\.)?(?:youtu\.be|youtube\.com)\/(?:(?:watch)?\?(?:.*&)?v(?:i)?=|(?:embed|v|vi|user)\/)([^\?&\"'>]+)/ );
                if( youtube_match && youtube_match[1].length == 11 )
                    return '<iframe src="//www.youtube.com/embed/' + youtube_match[1] + '" width="640" height="360" frameborder="0" allowfullscreen></iframe>';

                // vimeo - http://embedresponsively.com/
                var vimeo_match = url.match( /^(?:http(?:s)?:\/\/)?(?:[a-z0-9.]+\.)?vimeo\.com\/([0-9]+)$/ );
                if( vimeo_match )
                    return '<iframe src="//player.vimeo.com/video/' + vimeo_match[1] + '" width="640" height="360" frameborder="0" webkitAllowFullScreen mozallowfullscreen allowFullScreen></iframe>';

                // dailymotion - http://embedresponsively.com/
                var dailymotion_match = url.match( /^(?:http(?:s)?:\/\/)?(?:[a-z0-9.]+\.)?dailymotion\.com\/video\/([0-9a-z]+)$/ );
                if( dailymotion_match )
                    return '<iframe src="//www.dailymotion.com/embed/video/' + dailymotion_match[1] + '" width="640" height="360" frameborder="0" webkitAllowFullScreen mozallowfullscreen allowFullScreen></iframe>';

                // undefined -> create '<video/>' tag
            }
        })
        .change(function() {
            if( typeof console != 'undefined' )
                ;//console.log( 'change' );
        })
        .focus(function() {
            if( typeof console != 'undefined' )
                ;//console.log( 'focus' );
        })
        .blur(function() {
            if( typeof console != 'undefined' )
                ;//console.log( 'blur' );
        });

}




