$(function () {
    $('#jstree')
        .jstree({
            "plugins": ['sort','contextmenu','dnd'],
            'core': {
                "animation" : 2000,
                "check_callback" : true,
                'data': {
                    'url': function (node) {
                        return node.id === '#' ? 'tree?id=root' : 'tree?id=' + node.id;
                    },
                    'data': function (node) {
                        return { 'id': node.id };
                    }
                }
            }
        }).bind('move_node.jstree',function (e,data) {
        $.get('tree',{action:"move_category", id:data.node.id , old_parent:data.old_parent,new_parent:data.parent});})
        .bind('rename_node.jstree',function (e,data) {
            $.get('tree',{action:"rename_category", id: data.node.id , text: data.text});
        })
        .bind('create_node.jstree',function (e,data) {
            $.get('tree',{action:"create_category", position : data.parent, text : data.node.text});
            data.instance.refresh();
        })
        .bind('delete_node.jstree',function (e,data) {
            $.get('tree',{action:"delete_category", id: data.node.id});
        })

});