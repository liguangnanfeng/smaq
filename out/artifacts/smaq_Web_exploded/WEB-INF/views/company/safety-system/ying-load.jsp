<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>

  <h3 class="text-c mt-10">岗位应急处置卡</h3>
  <table id="xx_table" class="table table-border table-bordered table-bg table-hover table-sort">
    <tbody>
      <tr class="text-c tr-bq">
        <td>岗位名称</td>
        <td colspan="2">
          ${depat.name }
        </td>
      </tr>
      <tr class="text-c tr-bq">
        <td style="min-width:60px">序号</td>
        <td style="min-width:200px">本岗位存在的事故类型</td>
        <td>应急处置措施</td>
      </tr>
      <!-- <tr>
        <td class="text-c">1</td>
        <td></td>
        <td></td>
      </tr> -->
  </table>
  <table class="table table-border table-bordered table-bg table-hover table-sort" style="border-top:0;">
    <tbody>
      <tr class="text-c">
        <td colspan="4" style="border-top:0;">应急联系方式</td>
      </tr>
      <tr class="text-c">
        <td rowspan="2">内部</td>
        <td>主要负责人</td>
        <td>部门负责人</td>
        <td>工段负责人</td>
      </tr>
      <tr>
        <td><input type="text" id="fzr1" value="${yj.fzr1 }" class="input-text required" readonly="readonly" onclick="chosem3(this)" placeholder="请选择负责人"></td>
        <td><input type="text" id="fzr2" value="${yj.fzr2 }" class="input-text required" readonly="readonly" onclick="chosem3(this)" placeholder="请选择负责人"></td>
        <td><input type="text" id="fzr3" value="${yj.fzr3 }" class="input-text required" readonly="readonly" onclick="chosem3(this)" placeholder="请选择负责人"></td>
      </tr>
      <tr class="text-c">
        <td rowspan="2">外部</td>
        <td>火警电话</td>
        <td>急救电话</td>
        <td>报警电话</td>
      </tr>
      <tr class="text-c">
        <td>119</td>
        <td>120</td>
        <td>120</td>
      </tr>
    </tbody>
  </table>
  <input type="text" style="display: none;" id="id" value="${yj.id }">
  <input type="text" style="display: none;" id="shIds" value="${yj.shIds }">