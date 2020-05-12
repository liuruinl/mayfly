(window.webpackJsonp=window.webpackJsonp||[]).push([[7],{161:function(e,t,s){var i=s(36),a=s(176);"string"==typeof(a=a.__esModule?a.default:a)&&(a=[[e.i,a,""]]);var n={insert:"head",singleton:!1};i(a,n);e.exports=a.locals||{}},171:function(e,t,s){"use strict";s(175);var i=s(61),a=Object(i.a)({},(function(){var e=this.$createElement;return(this._self._c||e)("div",{staticClass:"toolbar"},[this._t("default")],2)}),[],!1,null,null,null);t.a=a.exports},175:function(e,t,s){"use strict";var i=s(161);s.n(i).a},176:function(e,t,s){(t=s(37)(!1)).push([e.i,"\n.toolbar {\n  width: 100%;\n  padding: 8px;\n  background-color: #ffffff;\n  overflow: hidden;\n  line-height: 32px;\n  border: 1px solid #e6ebf5;\n}\n",""]),e.exports=t},177:function(e,t,s){"use strict";var i=s(6),a=s.n(i),n=s(11),o=s.n(n),r=s(182),l=function(){function e(t){a()(this,e),this.url=t}return o()(e,[{key:"url",value:function(e){return this.url=e,this}},{key:"method",value:function(e){return this.method=e,this}},{key:"getUrl",value:function(){return r.a.getApiUrl(this.url)}},{key:"request",value:function(e){return r.a.send(this,e)}}],[{key:"url",value:function(t){return new e(t)}}]),e}();t.a=l},219:function(e,t,s){"use strict";s.d(t,"a",(function(){return a})),s.d(t,"c",(function(){return n})),s.d(t,"b",(function(){return o}));var i=s(12),a={machine:i.a.code("machine"),del:i.a.code("machine:delete"),machineFile:i.a.code("machineFile"),rmFile:i.a.code("machineFile:rm"),uploadFile:i.a.code("machineFile:upload"),updateFileContent:i.a.code("machineFile:updateFileContent"),addConf:i.a.code("machineFile:addConf")},n={redis:i.a.code("redis")},o={redisKey:i.a.code("redis:key"),del:i.a.code("redis:key:delete")}},220:function(e,t,s){"use strict";s.d(t,"a",(function(){return a})),s.d(t,"b",(function(){return n})),s.d(t,"c",(function(){return o}));var i=s(177),a={list:i.a.url("/devops/machines").method("get"),save:i.a.url("/devops/machines").method("post"),update:i.a.url("/devops/machines/{id}").method("put"),del:i.a.url("/devops/machines/{id}").method("delete"),files:i.a.url("/devops/machines/{id}/files").method("get"),lsFile:i.a.url("/devops/machines/files/{fileId}/ls").method("get"),rmFile:i.a.url("/devops/machines/files/{fileId}/rm").method("delete"),uploadFile:i.a.url("/devops/machines/files/upload"),fileContent:i.a.url("/devops/machines/files/{fileId}/cat").method("get"),updateFileContent:i.a.url("/devops/machines/files/{id}").method("put"),addConf:i.a.url("/devops/machines/{machineId}/files").method("post"),delConf:i.a.url("/devops/machines/files/{id}").method("delete")},n={list:i.a.url("/devops/redis").method("get"),info:i.a.url("/devops/redis/{id}/info").method("get"),del:i.a.url("/devops/redis/{id}").method("delete"),save:i.a.url("/devops/redis").method("post"),update:i.a.url("/devops/redis/{id}").method("put")},o={scan:i.a.url("/devops/redis/{cluster}/{id}/scan").method("get"),value:i.a.url("/devops/redis/{cluster}/{id}/value").method("get"),update:i.a.url("/devops/redis/{cluster}/{id}").method("put"),del:i.a.url("/devops/redis/{cluster}/{id}").method("delete")}},306:function(e,t){e.exports=function(e,t,s){return t in e?Object.defineProperty(e,t,{value:s,enumerable:!0,configurable:!0,writable:!0}):e[t]=s,e}},308:function(e,t,s){var i=s(36),a=s(745);"string"==typeof(a=a.__esModule?a.default:a)&&(a=[[e.i,a,""]]);var n={insert:"head",singleton:!1};i(a,n);e.exports=a.locals||{}},311:function(e,t,s){"use strict";s.d(t,"a",(function(){return d}));var i=s(306),a=s.n(i);function n(e,t){var s=Object.keys(e);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);t&&(i=i.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),s.push.apply(s,i)}return s}function o(e){for(var t=1;t<arguments.length;t++){var s=null!=arguments[t]?arguments[t]:{};t%2?n(Object(s),!0).forEach((function(t){a()(e,t,s[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(s)):n(Object(s)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(s,t))}))}return e}var r={name:"DynamicForm",props:{formInfo:Object,formData:[Object,Boolean]},data:function(){return{form:{},submitDisabled:!1}},watch:{formData:{handler:function(){this.formData&&(this.form=o({},this.formData))},deep:!0}},methods:{submit:function(){var e=this;this.$refs.dynamicForm.validate((function(t){if(!t)return!1;var s=o({},e.form),i=e.form.id?e.formInfo.updateApi:e.formInfo.createApi;i?(e.submitDisabled=!0,i.request(e.form).then((function(t){e.$message.success("保存成功"),e.$emit("submitSuccess",s),e.submitDisabled=!1}),(function(t){e.submitDisabled=!1}))):e.$message.error("表单未设置对应的提交权限")}))},reset:function(){this.$emit("reset"),this.resetFieldsAndData()},resetFieldsAndData:function(){this.$refs.dynamicForm.resetFields(),this.form={}}},mounted:function(){this.form=o({},this.formData)}},l=s(61),c=Object(l.a)(r,(function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{staticClass:"dynamic-form"},[s("el-form",{ref:"dynamicForm",attrs:{model:e.form,"label-width":e.formInfo.labelWidth?e.formInfo.labelWidth:"100px",size:e.formInfo.size?e.formInfo.size:"small"}},[e._l(e.formInfo.formRows,(function(t){return s("el-row",{key:t.key},e._l(t,(function(i){return s("el-col",{key:i.key,attrs:{span:i.span?i.span:24/t.length}},[s("el-form-item",{attrs:{prop:i.name,label:i.label,"label-width":i.labelWidth,required:i.required,rules:i.rules}},["input"===i.type?s("el-input",{attrs:{placeholder:i.placeholder,type:i.inputType,clearable:"",autocomplete:"new-password"},on:{change:function(t){i.change&&i.change(e.form)}},model:{value:e.form[i.name],callback:function(t){e.$set(e.form,i.name,"string"==typeof t?t.trim():t)},expression:"form[item.name]"}}):"text"===i.type?s("span",[e._v(e._s(e.form[i.name]))]):"select"===i.type?s("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:i.placeholder,filterable:i.filterable,remote:i.remote,"remote-method":i.remoteMethod,clearable:"",disabled:i.updateDisabled&&null!=e.form.id},on:{focus:function(t){i.focus&&i.focus(e.form)}},model:{value:e.form[i.name],callback:function(t){e.$set(e.form,i.name,"string"==typeof t?t.trim():t)},expression:"form[item.name]"}},e._l(i.options,(function(e){return s("el-option",{key:e.key,attrs:{label:e[i.optionProps&&i.optionProps.label||"label"],value:e[i.optionProps&&i.optionProps.value||"value"]}})})),1):e._e()],1)],1)})),1)})),e._v(" "),s("el-row",{attrs:{type:"flex",justify:"center"}},[e._t("btns",[s("el-button",{attrs:{size:"mini"},on:{click:e.reset}},[e._v("重 置")]),e._v(" "),s("el-button",{attrs:{type:"primary",size:"mini"},on:{click:e.submit}},[e._v("保 存")])],{submitDisabled:e.submitDisabled,data:e.form,submit:e.submit})],2)],2)],1)}),[],!1,null,null,null).exports,u={name:"DynamicFormDialog",props:{visible:Boolean,dialogWidth:String,title:String,formInfo:Object,formData:[Object,Boolean]},methods:{cancel:function(){var e=this;this.$emit("cancel"),setTimeout((function(){e.$refs.df.resetFieldsAndData()}),200)},submitSuccess:function(e){this.$emit("submitSuccess",e),this.cancel()}},mounted:function(){},components:{DynamicForm:c}},d=Object(l.a)(u,(function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{staticClass:"form-dialog"},[s("el-dialog",{attrs:{title:e.title,visible:e.visible,width:e.dialogWidth?e.dialogWidth:"500px"}},[s("dynamic-form",{ref:"df",attrs:{"form-info":e.formInfo,"form-data":e.formData},on:{submitSuccess:e.submitSuccess},scopedSlots:e._u([{key:"btns",fn:function(t){return[e._t("btns",[s("el-button",{attrs:{disabled:t.submitDisabled,type:"primary",size:"mini"},on:{click:t.submit}},[e._v("保 存")]),e._v(" "),s("el-button",{attrs:{disabled:t.submitDisabled,size:"mini"},on:{click:function(t){return e.cancel()}}},[e._v("取 消")])])]}}],null,!0)})],1)],1)}),[],!1,null,null,null).exports},744:function(e,t,s){"use strict";var i=s(308);s.n(i).a},745:function(e,t,s){(t=s(37)(!1)).push([e.i,"\n.row .title {\n  font-size: 12px;\n}\n.row .value {\n  font-size: 12px;\n  color: black;\n}\n",""]),e.exports=t},757:function(e,t,s){"use strict";s.r(t),s.d(t,"default",(function(){return v}));var i=s(173),a=s.n(i),n=s(174),o=s.n(n),r=s(171),l=(s(182),{props:{visible:Boolean,info:Object},data:function(){return{name:""}},methods:{close:function(){this.name="",this.$emit("close")}}}),c=(s(744),s(61)),u=Object(c.a)(l,(function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",[s("el-dialog",{attrs:{title:"info",visible:e.visible,"show-close":!0,width:"35%"},on:{close:function(t){return e.close()}}},[s("el-collapse",{model:{value:e.name,callback:function(t){e.name=t},expression:"name"}},[s("el-collapse-item",{attrs:{title:"Server(Redis服务器的一般信息)",name:"server"}},[s("div",{staticClass:"row"},[s("span",{staticClass:"title"},[e._v("redis_version(版本):")]),e._v(" "),s("span",{staticClass:"value"},[e._v(e._s(e.info.Server.redis_version))])]),e._v(" "),s("div",{staticClass:"row"},[s("span",{staticClass:"title"},[e._v("tcp_port(端口):")]),e._v(" "),s("span",{staticClass:"value"},[e._v(e._s(e.info.Server.tcp_port))])]),e._v(" "),s("div",{staticClass:"row"},[s("span",{staticClass:"title"},[e._v("redis_mode(模式):")]),e._v(" "),s("span",{staticClass:"value"},[e._v(e._s(e.info.Server.redis_mode))])]),e._v(" "),s("div",{staticClass:"row"},[s("span",{staticClass:"title"},[e._v("os(宿主操作系统):")]),e._v(" "),s("span",{staticClass:"value"},[e._v(e._s(e.info.Server.os))])]),e._v(" "),s("div",{staticClass:"row"},[s("span",{staticClass:"title"},[e._v("uptime_in_days(运行天数):")]),e._v(" "),s("span",{staticClass:"value"},[e._v(e._s(e.info.Server.uptime_in_days))])]),e._v(" "),s("div",{staticClass:"row"},[s("span",{staticClass:"title"},[e._v("executable(可执行文件路径):")]),e._v(" "),s("span",{staticClass:"value"},[e._v(e._s(e.info.Server.executable))])]),e._v(" "),s("div",{staticClass:"row"},[s("span",{staticClass:"title"},[e._v("config_file(配置文件路径):")]),e._v(" "),s("span",{staticClass:"value"},[e._v(e._s(e.info.Server.config_file))])])]),e._v(" "),s("el-collapse-item",{attrs:{title:"Clients(客户端连接部分)",name:"client"}},[s("div",{staticClass:"row"},[s("span",{staticClass:"title"},[e._v("connected_clients(已连接客户端数):")]),e._v(" "),s("span",{staticClass:"value"},[e._v(e._s(e.info.Clients.connected_clients))])])]),e._v(" "),s("el-collapse-item",{attrs:{title:"Memory(内存消耗相关信息)",name:"memory"}},[s("div",{staticClass:"row"},[s("span",{staticClass:"title"},[e._v("used_memory(分配内存总量):")]),e._v(" "),s("span",{staticClass:"value"},[e._v(e._s(e.info.Memory.used_memory_human))])]),e._v(" "),s("div",{staticClass:"row"},[s("span",{staticClass:"title"},[e._v("used_memory_rss(已分配的内存总量，操作系统角度):")]),e._v(" "),s("span",{staticClass:"value"},[e._v(e._s(e.info.Memory.used_memory_rss_human))])]),e._v(" "),s("div",{staticClass:"row"},[s("span",{staticClass:"title"},[e._v("mem_fragmentation_ratio(used_memory_rss和used_memory 之间的比率):")]),e._v(" "),s("span",{staticClass:"value"},[e._v(e._s(e.info.Memory.mem_fragmentation_ratio))])]),e._v(" "),s("div",{staticClass:"row"},[s("span",{staticClass:"title"},[e._v("used_memory_peak(内存消耗峰值):")]),e._v(" "),s("span",{staticClass:"value"},[e._v(e._s(e.info.Memory.used_memory_peak_human))])]),e._v(" "),s("div",{staticClass:"row"},[s("span",{staticClass:"title"},[e._v("total_system_memory(主机总内存):")]),e._v(" "),s("span",{staticClass:"value"},[e._v(e._s(e.info.Memory.total_system_memory_human))])])]),e._v(" "),s("el-collapse-item",{attrs:{title:"CPU",name:"cpu"}},[s("div",{staticClass:"row"},[s("span",{staticClass:"title"},[e._v("used_cpu_sys(由Redis服务器消耗的系统CPU):")]),e._v(" "),s("span",{staticClass:"value"},[e._v(e._s(e.info.CPU.used_cpu_sys))])]),e._v(" "),s("div",{staticClass:"row"},[s("span",{staticClass:"title"},[e._v("used_cpu_user(由Redis服务器消耗的用户CPU):")]),e._v(" "),s("span",{staticClass:"value"},[e._v(e._s(e.info.CPU.used_cpu_user))])]),e._v(" "),s("div",{staticClass:"row"},[s("span",{staticClass:"title"},[e._v("used_cpu_sys_children(由后台进程消耗的系统CPU):")]),e._v(" "),s("span",{staticClass:"value"},[e._v(e._s(e.info.CPU.used_cpu_sys_children))])]),e._v(" "),s("div",{staticClass:"row"},[s("span",{staticClass:"title"},[e._v("used_cpu_user_children(由后台进程消耗的用户CPU):")]),e._v(" "),s("span",{staticClass:"value"},[e._v(e._s(e.info.CPU.used_cpu_user_children))])])])],1)],1)],1)}),[],!1,null,null,null).exports,d=s(219),m=s(220),f=s(311),p={data:function(){return{redisTable:[],permission:d.c,keyPermission:d.b,currentId:null,currentData:null,params:{host:null,clusterId:null},redisInfo:{url:""},clusters:[{id:0,name:"单机"}],infoDialog:{visible:!1,info:{Server:{},Keyspace:{},Clients:{},CPU:{},Memory:{}}},formDialog:{visible:!1,title:null,formInfo:{createApi:m.b.save,updateApi:m.b.update,formRows:[[{type:"input",label:"主机：",name:"host",placeholder:"请输入节点ip",rules:[{required:!0,message:"请输入节点ip",trigger:["blur","change"]}]}],[{type:"input",label:"端口号：",name:"port",placeholder:"请输入节点端口号",inputType:"number",rules:[{required:!0,message:"请输入节点端口号",trigger:["blur","change"]}]}],[{type:"input",label:"密码：",name:"pwd",placeholder:"请输入节点密码",inputType:"password"}],[{type:"input",label:"描述：",name:"description",placeholder:"请输入节点描述",inputType:"textarea"}]]},formData:null}}},methods:{choose:function(e){e&&(this.currentId=e.id,this.currentData=e)},connect:function(){var e=this;Req.post("/open/redis/connect",this.form,(function(t){e.redisInfo=t}))},deleteNode:function(){var e=this;return o()(a.a.mark((function t(){return a.a.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.next=2,m.b.del.request({id:e.currentId});case 2:e.$message.success("删除成功"),e.search();case 4:case"end":return t.stop()}}),t)})))()},manage:function(e){this.$router.push("/redis_operation/".concat(e.clusterId,"/").concat(e.id))},info:function(e){var t=this;m.b.info.request({id:e}).then((function(s){t.infoDialog.info=s,t.infoDialog.id=e,t.infoDialog.visible=!0}))},search:function(){var e=this;m.b.list.request(this.params).then((function(t){e.redisTable=t}))},openFormDialog:function(e){var t;e?(this.formDialog.formData=this.currentData,t="编辑redis节点"):(this.formDialog.formData={port:6379},t="添加redis节点"),this.formDialog.title=t,this.formDialog.visible=!0},closeDialog:function(){this.formDialog.visible=!1,this.formDialog.formData=null},submitSuccess:function(){this.currentId=null,this.currentData=null,this.search()}},mounted:function(){this.search()},components:{ToolBar:r.a,Info:u,DynamicFormDialog:f.a}},v=Object(c.a)(p,(function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",[s("ToolBar",[s("el-button",{attrs:{type:"primary",icon:"el-icon-plus",size:"mini",plain:""},on:{click:function(t){return e.openFormDialog(!1)}}},[e._v("添加")]),e._v(" "),s("el-button",{attrs:{type:"primary",icon:"el-icon-edit",disabled:null==e.currentId,size:"mini",plain:""},on:{click:function(t){return e.openFormDialog(e.currentData)}}},[e._v("编辑")]),e._v(" "),s("el-button",{attrs:{type:"danger",icon:"el-icon-delete",disabled:null==e.currentId,size:"mini",plain:""},on:{click:e.deleteNode}},[e._v("删除")]),e._v(" "),s("div",{staticStyle:{float:"right"}},[s("el-input",{staticStyle:{width:"140px"},attrs:{placeholder:"host",size:"mini",plain:"",clearable:""},on:{clear:e.search},model:{value:e.params.host,callback:function(t){e.$set(e.params,"host",t)},expression:"params.host"}}),e._v(" "),s("el-select",{attrs:{size:"mini",clearable:"",placeholder:"集群选择"},model:{value:e.params.clusterId,callback:function(t){e.$set(e.params,"clusterId",t)},expression:"params.clusterId"}},e._l(e.clusters,(function(e){return s("el-option",{key:e.id,attrs:{value:e.id,label:e.name}})})),1),e._v(" "),s("el-button",{attrs:{type:"success",icon:"el-icon-search",size:"mini"},on:{click:e.search}})],1)],1),e._v(" "),s("el-table",{staticStyle:{width:"100%"},attrs:{data:e.redisTable,stripe:""},on:{"current-change":e.choose}},[s("el-table-column",{attrs:{label:"选择",width:"50px"},scopedSlots:e._u([{key:"default",fn:function(t){return[s("el-radio",{attrs:{label:t.row.id},model:{value:e.currentId,callback:function(t){e.currentId=t},expression:"currentId"}},[s("i")])]}}])}),e._v(" "),s("el-table-column",{attrs:{prop:"host",label:"IP",width:""}}),e._v(" "),s("el-table-column",{attrs:{prop:"port",label:"端口",width:""}}),e._v(" "),s("el-table-column",{attrs:{prop:"clusterId",label:"集群id"}}),e._v(" "),s("el-table-column",{attrs:{prop:"description",label:"描述"}}),e._v(" "),s("el-table-column",{attrs:{label:"操作",width:""},scopedSlots:e._u([{key:"default",fn:function(t){return[s("el-button",{directives:[{name:"permission",rawName:"v-permission",value:e.permission.redis.code,expression:"permission.redis.code"}],ref:t.row,attrs:{type:"primary",icom:"el-icon-tickets",size:"mini",plain:""},on:{click:function(s){return e.info(t.row.id)}}},[e._v("info")]),e._v(" "),s("el-button",{directives:[{name:"permission",rawName:"v-permission",value:e.keyPermission.redisKey.code,expression:"keyPermission.redisKey.code"}],ref:t.row,attrs:{type:"success",size:"mini",plain:""},on:{click:function(s){return e.manage(t.row)}}},[e._v("数据管理")])]}}])})],1),e._v(" "),s("Info",{attrs:{visible:e.infoDialog.visible,info:e.infoDialog.info},on:{close:function(t){e.infoDialog.visible=!1}}}),e._v(" "),s("dynamic-form-dialog",{attrs:{"dialog-width":e.formDialog.dialogWidth,visible:e.formDialog.visible,title:e.formDialog.title,"form-info":e.formDialog.formInfo,"form-data":e.formDialog.formData},on:{cancel:e.closeDialog,submitSuccess:e.submitSuccess}})],1)}),[],!1,null,null,null).exports}}]);