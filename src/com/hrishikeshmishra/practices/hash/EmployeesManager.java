package com.hrishikeshmishra.practices.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Problem:
 * Find number of Employees Under every Employee
 * Given a dictionary that contains mapping of employee and his manager as a
 * number of (employee, manager) pairs like below.
 * ;
 * For example:
 * { "A", "C" },
 * { "B", "C" },
 * { "C", "F" },
 * { "D", "E" },
 * { "E", "F" },
 * { "F", "F" }
 * ;
 * In this example C is manager of A,
 * C is also manager of B, F is manager  of C and so on.
 * ;
 * Write a function to get no of employees under each manager in the hierarchy not just their direct reports.
 * It may be assumed that an employee directly reports to only one manager. In the above dictionary the
 * root node/ceo is listed as reporting to himself.
 * ;
 * Output should be a Dictionary that contains following.
 * ;
 * A - 0
 * B - 0
 * C - 2
 * D - 0
 * E - 1
 * F - 5
 * ;
 * ;
 * ;
 * Solution:
 * - Easy to build topological graph for each employee and count
 * - Either use hash for this
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/find-number-employees-every-employee/
 */
public class EmployeesManager {

    public static Map<String, Integer> getReporteeCount(Map<String, String> employeeManagerMap) {

        /** Get Manager Employee Map **/
        Map<String, List<String>> managerEmployeeMap = getManagerEmployeeMap(employeeManagerMap);

        Map<String, Integer> reporteeCountMap = new HashMap<>();

        /** Count reportee for each employee **/
        for (String employee : employeeManagerMap.keySet()) {
            calculateEmployeeReportee(employee, managerEmployeeMap, reporteeCountMap);
        }

        return reporteeCountMap;
    }

    private static void calculateEmployeeReportee(String manager, Map<String, List<String>> managerEmployeeMap,
                                                  Map<String, Integer> reporteeCountMap) {

        /** When manager not contain in manageEmployeeMap **/
        if (!managerEmployeeMap.containsKey(manager)) {
            reporteeCountMap.put(manager, 0);
            return;
        }

        /** Add Direct reportee to totalReportee **/
        int totalReportee = managerEmployeeMap.get(manager).size();

        for (String reportee : managerEmployeeMap.get(manager)) {

            /** If reportees are not calculate for current reportee, then first calculate it **/
            if (!reporteeCountMap.containsKey(reportee)) {
                calculateEmployeeReportee(reportee, managerEmployeeMap, reporteeCountMap);
            }

            /** Update indirect reportee **/
            totalReportee += reporteeCountMap.get(reportee);
        }

        /** Update result set  **/
        reporteeCountMap.put(manager, totalReportee);

    }

    private static Map<String, List<String>> getManagerEmployeeMap(Map<String, String> employeeManagerMap) {

        Map<String, List<String>> managerEmployeeMap = new HashMap<>();

        for (Map.Entry<String, String> entry : employeeManagerMap.entrySet()) {

            /** Special case when employee is self reportee, specially happen topmost employee **/
            if (entry.getKey().equals(entry.getValue())) {
                continue;
            }
            addEmployeeToManager(managerEmployeeMap, entry);
        }

        return managerEmployeeMap;
    }

    private static void addEmployeeToManager(Map<String, List<String>> managerEmployeeMap,
                                             final Map.Entry<String, String> entry) {

        if (managerEmployeeMap.containsKey(entry.getValue())) {
            managerEmployeeMap.get(entry.getValue()).add(entry.getKey());
        } else {
            List<String> employees = new ArrayList<String>() {
                {
                    add(entry.getKey());
                }
            };
            managerEmployeeMap.put(entry.getValue(), employees);
        }
    }

}


class EmployeesManagerTest {

    public static void main(String[] args) {
        Map<String, String> employeeManagerMap = new HashMap<String, String>() {
            {
                put("A", "C");
                put("B", "C");
                put("C", "F");
                put("D", "E");
                put("E", "F");
                put("F", "F");
            }
        };
        Map<String, Integer> reporteeCount = EmployeesManager.getReporteeCount(employeeManagerMap);
        System.out.println("Result: " + reporteeCount);
    }


}