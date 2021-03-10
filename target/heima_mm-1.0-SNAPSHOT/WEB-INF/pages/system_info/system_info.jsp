<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../base.jsp" %>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            黑马面面
            <small>后台功能</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="#">后台管理</a></li>
            <li class="active">系统</li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">系统信息监控</h3>
            </div>
            <div class="box-body">
                <div id="div" class="king-page-box">
                    <div class="king-container clearfix">
                        <div class="container-fluid mb0 ">
                            <div class="row">
                                <div class="col-md-6">
                                    <!--CPU监控 -->
                                    <div class="panel king-panel1 mb0 m10 ">
                                        <div class="panel-heading king-panel-head1 king-panel-top2">
                                            <h2 class="panel-title" style="font-size: 16px"><b>cpu监控</b></h2>
                                        </div>
                                        <div class="panel-body p0">
                                            <table class="table mb0 pr15 ranger-box ">
                                                <thead>
                                                <tr>
                                                    <th style="width: 50%;">属性</th>
                                                    <th style="width: 50%;">值</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <td>核心数</td>
                                                    <td>${cpu.cpuNum}核</td>
                                                </tr>
                                                <tr>
                                                    <td>系统使用率</td>
                                                    <td>${cpu.sys}</td>
                                                </tr>
                                                <tr>
                                                    <td>用户使用率</td>
                                                    <td>${cpu.used}%</td>
                                                </tr>
                                                <tr>
                                                    <td>当前空闲率</td>
                                                    <td>${cpu.free}%</td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <!--内存监控-->
                                    <div class="panel king-panel1 mb0 m10 ">
                                        <div class="panel-heading king-panel-head1 king-panel-top4">
                                            <h3 class="panel-title" style="font-size: 16px">内存监控</h3>
                                        </div>
                                        <div class="panel-body p0">
                                            <table class="table mb0 pr15 ranger-box ">
                                                <thead>
                                                <thead>
                                                <tr>
                                                    <th style="width: 33%">属性</th>
                                                    <th style="width: 33%;">内存</th>
                                                    <th style="width: 33%;">JVM</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <td>总内存</td>
                                                    <td>${mem.total}GB</td>
                                                    <td>${jvm.max}MB</td>
                                                </tr>
                                                <tr>
                                                    <td>已用内存</td>
                                                    <td>${mem.used}GB</td>
                                                    <td>${jvm.total}MB</td>
                                                </tr>
                                                <tr>
                                                    <td>空闲内存</td>
                                                    <td>${mem.free}GB</td>
                                                    <td>${jvm.free}MB</td>
                                                </tr>
                                                <tr>
                                                    <td>当前空闲率</td>
                                                    <td>${mem.free}GB</td>
                                                    <td>${jvm.free}MB</td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--服务器信息-->
                        <div class="container-fluid mb0 " style="margin-top: 30px">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="panel king-panel1 mb0 m10 ">
                                        <div class="panel-heading king-panel-head1 king-panel-top5">
                                            <h2 class="panel-title" style="font-size: 16px"><b>服务器监控</b></h2>
                                        </div>
                                        <div class="panel-body p0">
                                            <table class="table mb0 pr15 ranger-box ">
                                                <thead>
                                                <tr>
                                                    <th>服务器名称</th>
                                                    <th>操作系统</th>
                                                    <th>服务器IP</th>
                                                    <th>系统架构</th>
                                                    <th>服务器路径</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <td style="width: 20%;">${sys.computerName}</td>
                                                    <td style="width: 20%;">${sys.osName}</td>
                                                    <td style="width: 20%;">${sys.computerIp}</td>
                                                    <td style="width: 20%;">${sys.osArch}</td>
                                                    <td style="width: 20%;">${sys.userDir}</td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--Java虚拟机-->
                        <div class="container-fluid mb0 " style="margin-top: 30px">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="panel king-panel1 mb0 m10 ">
                                        <div class="panel-heading king-panel-head1 king-panel-top3">
                                            <h2 class="panel-title" style="font-size: 16px"><b>Java虚拟机</b></h2>
                                        </div>
                                        <div class="panel-body p0">
                                            <table class="table mb0 pr15 ranger-box ">
                                                <thead>
                                                <tr>
                                                    <td style="width: 15%;">Java名称</td>
                                                    <td style="width: 35%;">${jvm.name}</td>
                                                    <td style="width: 15%;">Java版本</td>
                                                    <td style="width: 35%;">${jvm.version}</td>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <td style="width: 15%;">启动时间</td>
                                                    <td style="width: 35%;">${jvm.startTime}</td>
                                                    <td style="width: 15%;">运行时长</td>
                                                    <td style="width: 35%;">${jvm.runTime}</td>
                                                </tr>
                                                <tr>
                                                    <td style="width: 15%;">安装路径</td>
                                                    <td style="width: 35%;">${jvm.home}</td>
                                                    <td style="width: 15%;"></td>
                                                    <td style="width: 35%;"></td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--磁盘监控-->
                        <div class="container-fluid mb0 " style="margin-top: 30px">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="panel king-panel1 mb0 m10 ">
                                        <div class="panel-heading king-panel-head1 king-panel-top6">
                                            <h1 class="panel-title" style="font-size: 16px"><b>磁盘监控</b></h1>
                                        </div>
                                        <div class="panel-body p0">
                                            <table class="table table-bordered table-hover table-striped">
                                                <tr>
                                                    <th>盘符路径</th>
                                                    <th>文件系统</th>
                                                    <th>盘符类型</th>
                                                    <th>总大小</th>
                                                    <th>可用大小</th>
                                                    <th>已用大小</th>
                                                    <th>已用百分比</th>
                                                </tr>

                                                <%--@elvariable id="newsList" type="java.util.List"--%>
                                                <c:forEach items="${sysFiles}" var="files">
                                                <tr>
                                                    <td>${files.dirName}</td>
                                                    <td>${files.sysTypeName}</td>
                                                    <td>${files.typeName}</td>
                                                    <td>${files.total}</td>
                                                    <td>${files.free}</td>
                                                    <td>${files.used}</td>
                                                    <td>${files.usage}%</td>
                                                </tr>
                                                </c:forEach>
                                           <%-- <el-table :data="sysFiles" stripe style="width: 100%">
                                                <el-table-column prop="dirName" label="盘符路径"
                                                                 style="width: 14%;"></el-table-column>
                                                <el-table-column prop="sysTypeName" label="盘符类型"
                                                                 style="width: 14%;"></el-table-column>
                                                <el-table-column prop="typeName" label="文件类型"
                                                                 style="width: 14%;"></el-table-column>
                                                <el-table-column prop="total" label="总大小"
                                                                 style="width: 14%;"></el-table-column>
                                                <el-table-column prop="free" label="剩余大小"
                                                                 style="width: 14%;"></el-table-column>
                                                <el-table-column prop="usage" label="已经使用量"
                                                                 style="width: 14%;"></el-table-column>
                                                <el-table-column prop="used" label="资源的使用率"
                                                                 style="width: 14%;"></el-table-column>
                                            </el-table>--%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- 正文区域 /-->
</div>
