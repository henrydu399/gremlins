! function(t, e) {
  "use strict";
  t.module("smart-table", []).run(["$templateCache", function(t) {
      t.put("template/smart-table/pagination.html", '<nav ng-if="numPages && pages.length >= 2"><ul class="pagination"><li ng-repeat="page in pages" ng-class="{active: page==currentPage}"><a href="javascript: void(0);" ng-click="selectPage(page)">{{page}}</a></li></ul></nav>')
  }]), t.module("smart-table").constant("stConfig", {
      pagination: {
          template: "template/smart-table/pagination.html",
          itemsByPage: 10,
          displayedPages: 5
      },
      search: {
          delay: 400,
          inputEvent: "input"
      },
      select: {
          mode: "single",
          selectedClass: "st-selected"
      },
      sort: {
          ascentClass: "st-sort-ascent",
          descentClass: "st-sort-descent",
          descendingFirst: !1,
          skipNatural: !1,
          delay: 300
      },
      pipe: {
          delay: 100
      }
  }), t.module("smart-table").controller("stTableController", ["$scope", "$parse", "$filter", "$attrs", function(a, n, s, i) {
      function r(t) {
          return t ? [].concat(t) : []
      }

      function l() {
          b = r(o(a)), v === !0 && S.pipe()
      }

      function c(t, e) {
          if (-1 != e.indexOf(".")) {
              var a = e.split("."),
                  s = a.pop(),
                  i = a.join("."),
                  r = n(i)(t);
              delete r[s], 0 == Object.keys(r).length && c(t, i)
          } else delete t[e]
      }
      var o, u, p, g = i.stTable,
          d = n(g),
          f = d.assign,
          m = s("orderBy"),
          h = s("filter"),
          b = r(d(a)),
          P = {
              sort: {},
              search: {},
              pagination: {
                  start: 0,
                  totalItemCount: 0
              }
          },
          v = !0,
          S = this;
      i.stSafeSrc && (o = n(i.stSafeSrc), a.$watch(function() {
          var t = o(a);
          return t && t.length ? t[0] : e
      }, function(t, e) {
          t !== e && l()
      }), a.$watch(function() {
          var t = o(a);
          return t ? t.length : 0
      }, function(t) {
          t !== b.length && l()
      }), a.$watch(function() {
          return o(a)
      }, function(t, e) {
          t !== e && (P.pagination.start = 0, l())
      })), this.sortBy = function(e, a) {
          return P.sort.predicate = e, P.sort.reverse = a === !0, t.isFunction(e) ? P.sort.functionName = e.name : delete P.sort.functionName, P.pagination.start = 0, this.pipe()
      }, this.search = function(e, a) {
          var s = P.search.predicateObject || {},
              i = a ? a : "$";
          return e = t.isString(e) ? e.trim() : e, n(i).assign(s, e), e || c(s, i), P.search.predicateObject = s, P.pagination.start = 0, this.pipe()
      }, this.pipe = function() {
          var t, n = P.pagination;
          u = P.search.predicateObject ? h(b, P.search.predicateObject) : b, P.sort.predicate && (u = m(u, P.sort.predicate, P.sort.reverse)), n.totalItemCount = u.length, n.number !== e && (n.numberOfPages = u.length > 0 ? Math.ceil(u.length / n.number) : 1, n.start = n.start >= u.length ? (n.numberOfPages - 1) * n.number : n.start, t = u.slice(n.start, n.start + parseInt(n.number))), f(a, t || u)
      }, this.select = function(t, n) {
          var s = r(d(a)),
              i = s.indexOf(t); - 1 !== i && ("single" === n ? (t.isSelected = t.isSelected !== !0, p && (p.isSelected = !1), p = t.isSelected === !0 ? t : e) : s[i].isSelected = !s[i].isSelected)
      }, this.slice = function(t, e) {
          return P.pagination.start = t, P.pagination.number = e, this.pipe()
      }, this.tableState = function() {
          return P
      }, this.getFilteredCollection = function() {
          return u || b
      }, this.setFilterFunction = function(t) {
          h = s(t)
      }, this.setSortFunction = function(t) {
          m = s(t)
      }, this.preventPipeOnWatch = function() {
          v = !1
      }
  }]).directive("stTable", function() {
      return {
          restrict: "A",
          controller: "stTableController",
          link: function(t, e, a, n) {
              a.stSetFilter && n.setFilterFunction(a.stSetFilter), a.stSetSort && n.setSortFunction(a.stSetSort)
          }
      }
  }), t.module("smart-table").directive("stSearch", ["stConfig", "$timeout", "$parse", function(t, e, a) {
      return {
          require: "^stTable",
          link: function(n, s, i, r) {
              var l = r,
                  c = null,
                  o = i.stDelay || t.search.delay,
                  u = i.stInputEvent || t.search.inputEvent;
              i.$observe("stSearch", function(t, e) {
                  var a = s[0].value;
                  t !== e && a && (r.tableState().search = {}, l.search(a, t))
              }), n.$watch(function() {
                  return r.tableState().search
              }, function(t) {
                  var e = i.stSearch || "$";
                  t.predicateObject && a(e)(t.predicateObject) !== s[0].value && (s[0].value = a(e)(t.predicateObject) || "")
              }, !0), s.bind(u, function(t) {
                  t = t.originalEvent || t, null !== c && e.cancel(c), c = e(function() {
                      l.search(t.target.value, i.stSearch || ""), c = null
                  }, o)
              })
          }
      }
  }]), t.module("smart-table").directive("stSelectRow", ["stConfig", function(t) {
      return {
          restrict: "A",
          require: "^stTable",
          scope: {
              row: "=stSelectRow"
          },
          link: function(e, a, n, s) {
              var i = n.stSelectMode || t.select.mode;
              a.bind("click", function() {
                  e.$apply(function() {
                      s.select(e.row, i)
                  })
              }), e.$watch("row.isSelected", function(e) {
                  e === !0 ? a.addClass(t.select.selectedClass) : a.removeClass(t.select.selectedClass)
              })
          }
      }
  }]), t.module("smart-table").directive("stSort", ["stConfig", "$parse", "$timeout", function(a, n, s) {
      return {
          restrict: "A",
          require: "^stTable",
          link: function(i, r, l, c) {
              function o() {
                  P ? d = 0 === d ? 2 : d - 1 : d++;
                  var e;
                  p = t.isFunction(g(i)) || t.isArray(g(i)) ? g(i) : l.stSort, d % 3 === 0 && !!b != !0 ? (d = 0, c.tableState().sort = {}, c.tableState().pagination.start = 0, e = c.pipe.bind(c)) : e = c.sortBy.bind(c, p, d % 2 === 0), null !== v && s.cancel(v), 0 > S ? e() : v = s(e, S)
              }
              var u, p = l.stSort,
                  g = n(p),
                  d = 0,
                  f = l.stClassAscent || a.sort.ascentClass,
                  m = l.stClassDescent || a.sort.descentClass,
                  h = [f, m],
                  b = l.stSkipNatural !== e ? l.stSkipNatural : a.sort.skipNatural,
                  P = l.stDescendingFirst !== e ? l.stDescendingFirst : a.sort.descendingFirst,
                  v = null,
                  S = l.stDelay || a.sort.delay;
              l.stSortDefault && (u = i.$eval(l.stSortDefault) !== e ? i.$eval(l.stSortDefault) : l.stSortDefault), r.bind("click", function() {
                  p && i.$apply(o)
              }), u && (d = "reverse" === u ? 1 : 0, o()), i.$watch(function() {
                  return c.tableState().sort
              }, function(t) {
                  t.predicate !== p ? (d = 0, r.removeClass(f).removeClass(m)) : (d = t.reverse === !0 ? 2 : 1, r.removeClass(h[d % 2]).addClass(h[d - 1]))
              }, !0)
          }
      }
  }]), t.module("smart-table").directive("stPagination", ["stConfig", function(t) {
      return {
          restrict: "EA",
          require: "^stTable",
          scope: {
              stItemsByPage: "=?",
              stDisplayedPages: "=?",
              stPageChange: "&"
          },
          templateUrl: function(e, a) {
              return a.stTemplate ? a.stTemplate : t.pagination.template
          },
          link: function(e, a, n, s) {
              function i() {
                  var t, a, n = s.tableState().pagination,
                      i = 1,
                      r = e.currentPage;
                  for (e.totalItemCount = n.totalItemCount, e.currentPage = Math.floor(n.start / n.number) + 1, i = Math.max(i, e.currentPage - Math.abs(Math.floor(e.stDisplayedPages / 2))), t = i + e.stDisplayedPages, t > n.numberOfPages && (t = n.numberOfPages + 1, i = Math.max(1, t - e.stDisplayedPages)), e.pages = [], e.numPages = n.numberOfPages, a = i; t > a; a++) e.pages.push(a);
                  r !== e.currentPage && e.stPageChange({
                      newPage: e.currentPage
                  })
              }
              e.stItemsByPage = e.stItemsByPage ? +e.stItemsByPage : t.pagination.itemsByPage, e.stDisplayedPages = e.stDisplayedPages ? +e.stDisplayedPages : t.pagination.displayedPages, e.currentPage = 1, e.pages = [], e.$watch(function() {
                  return s.tableState().pagination
              }, i, !0), e.$watch("stItemsByPage", function(t, a) {
                  t !== a && e.selectPage(1)
              }), e.$watch("stDisplayedPages", i), e.selectPage = function(t) {
                  t > 0 && t <= e.numPages && s.slice((t - 1) * e.stItemsByPage, e.stItemsByPage)
              }, s.tableState().pagination.number || s.slice(0, e.stItemsByPage)
          }
      }
  }]), t.module("smart-table").directive("stPipe", ["stConfig", "$timeout", function(e, a) {
      return {
          require: "stTable",
          scope: {
              stPipe: "="
          },
          link: {
              pre: function(n, s, i, r) {
                  var l = null;
                  t.isFunction(n.stPipe) && (r.preventPipeOnWatch(), r.pipe = function() {
                      return null !== l && a.cancel(l), l = a(function() {
                          n.stPipe(r.tableState(), r)
                      }, e.pipe.delay)
                  })
              },
              post: function(t, e, a, n) {
                  n.pipe()
              }
          }
      }
  }])
}(angular);