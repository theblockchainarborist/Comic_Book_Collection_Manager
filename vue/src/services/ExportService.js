import exportFromJSON from 'export-from-json';

// Source: https://www.nightprogrammer.com/vue-js/export-data-to-excel-csv-in-vuejs-example/

export const excelParser = () => {
    function exportDataFromJSON(data, newFileName, fileExportType) {
        if (!data) {
            return;
        }
        try {
            const fileName = newFileName || "exported-data";
            const exportType = exportFromJSON.types[fileExportType || "csv"];
            exportFromJSON({data, fileName, exportType});
        } catch (e) {
            throw new Error("Error: Unable to parse data.");
        }
    }

    return {
        exportDataFromJSON
    };
};