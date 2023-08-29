package dat250exp1;

import io.javalin.Javalin;

public class App {

    private static final String WEBPAGE = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <title>Convert units</title>
            </head>
            <body>
            <h1>Unit converter</h1>
            <form action="/convert" method="post">
                <fieldset>
                <label for="val">Value:</label>    <input id="val" type="text" name="value"><br />
                <label for="source-unit">From unit:</label>
                <select name="sunit" id="source-unit">
                    <option value="in">Inches</option>
                    <option value="ft">Feet</option>
                    <option value="mi">Miles</option>
                    <option value="m">Metres</option>
                </select><br />
                <label for="target-unit">To unit:</label>
                <select name="tunit" id="target-unit">
                    <option value="in">Inches</option>
                    <option value="ft">Feet</option>
                    <option value="mi">Miles</option>
                    <option value="m">Metres</option>
                </select><br />
                <input type="submit" value="Calculate" />
                </fieldset>
            </form>
            </body>
            </html>""";

    private static final double IN_TO_METER = 0.0254;
    private static final double FT_TO_METER = 0.3048;
    private static final double MI_TO_METER = 1609.344;


    public static void main(String[] args) {
        Javalin.create()
                .get("/", ctx -> ctx.html(WEBPAGE))
                .post("/convert", ctx -> {
                    double value = Double.parseDouble(ctx.formParam("value"));
                    String fromUnit = ctx.formParam("sunit");
                    String toUnit = ctx.formParam("tunit");
                    double result = convertUnit(fromUnit, toUnit, value);
                    ctx.result(Double.toString(result));
                })
                .start(9000);
    }

    /**
     * Performs the unit conversion
     * @param fromUnit Unit to be converted from
     * @param toUnit Unit to be converted into
     * @param value Amount of 'fromUnit' to convert
     * @return The resulting amount of 'toUnit'
     */
    public static double convertUnit(String fromUnit, String toUnit, double value) {
        double inMeters = switch (fromUnit) {
            case "in" -> value * IN_TO_METER;
            case "ft" -> value * FT_TO_METER;
            case "mi" -> value * MI_TO_METER;
            case "m" -> value;
            default -> Double.NaN;
        };
        double result = switch (toUnit) {
            case "in" -> inMeters / IN_TO_METER;
            case "ft" -> inMeters / FT_TO_METER;
            case "mi" -> inMeters / MI_TO_METER;
            case "m" -> inMeters;
            default -> Double.NaN;
        };
        return result;
    }


}
