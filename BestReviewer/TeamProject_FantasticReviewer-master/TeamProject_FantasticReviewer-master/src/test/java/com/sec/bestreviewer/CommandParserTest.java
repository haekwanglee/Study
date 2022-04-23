package com.sec.bestreviewer;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CommandParserTest {

    private static final String OPTION_PRINT = "-p";
    private static final String OPTION_FIRST_NAME = "-f";
    private static final String OPTION_SMALL_SIGN = "-s";
    private static final String OPTION_LAST_PHONE = "-l";
    private static final String OPTION_AND = "-a";
    private static final String OPTION_MONTH = "-m";
    private static final String OPTION_OR = "-o";
    private static final String OPTION_YEAR = "-y";
    private static final String EMPTY = " ";
    private static final String OPTION_DAY = "-d";
    private static final String BIRTHDAY = "birthday";
    private static final String CL = "cl";
    private static final String NAME = "name";
    private static final String PHONE_NUM = "phoneNum";
    private static final String CERTI = "certi";
    private static final String ADD = "ADD";
    private static final String DEL = "DEL";
    private static final String MOD = "MOD";
    private static final String SCH = "SCH";

    private CommandParser commandParser;

    @BeforeAll
    void setup() {
        commandParser = new CommandParser();
    }

    @Nested
    @DisplayName("IllegalArgumentException test")
    class IllegalArgument {
        @Test
        @DisplayName("지원하지 않는 CMD인 경우")
        void testIllegalArgumentException() {
            String line = "NONE, , , ,08951033,QDJPTOJ KIM,CL3,010-3240-5443,19800308,PRO";
            TokenGroup tokens = commandParser.parse(line);
            assertThrows(IllegalArgumentException.class, () -> CommandFactory.buildCommand(tokens));
        }

        @Test
        @DisplayName("parameter 수가 맞지 않는 경우")
        void testIllegalArgumentNumber() {
            String line = "ADD, , , ";
            assertThrows(IllegalArgumentException.class, () -> commandParser.parse(line));
        }
    }

    @Nested
    @DisplayName("옵션이 없는 경우")
    class Option {
        @Test
        @DisplayName("옵션이_없는_ADD를_잘파싱하는지_확인")
        void testAddCommand() {
            String line = "ADD, , , ,08951033,QDJPTOJ KIM,CL3,010-3240-5443,19800308,PRO";

            TokenGroup tokens = commandParser.parse(line);

            assertEquals(ADD, tokens.getType());

            String[] params = {"08951033", "QDJPTOJ KIM", "CL3", "010-3240-5443", "19800308", "PRO"};
            assertEquals(params[0], tokens.getEmployee().getEmployeeNumber());
            assertEquals(params[1], tokens.getEmployee().getName());
            assertEquals(params[2], tokens.getEmployee().getCareerLevel());
            assertEquals(params[3], tokens.getEmployee().getPhoneNumber());
            assertEquals(params[4], tokens.getEmployee().getBirthday());
            assertEquals(params[5], tokens.getEmployee().getCerti());
        }
    }

    @Nested
    @DisplayName("옵션이 있는 경우")
    class NoOption {
        @Nested
        @DisplayName("단일 옵션")
        class OptionOnce {
            @Test
            @DisplayName("단일_첫째_옵션만_있는_CMD를_잘파싱하는지_확인")
            void testCMDwithOption1() {
                String line = "SCH,-p, , ,phoneNum,010-2742-2901";

                TokenGroup tokens = commandParser.parse(line);

                assertEquals(SCH, tokens.getType());

                assertEquals(PHONE_NUM, tokens.getConditionParameter().getFirst().getFieldName());
                assertEquals("010-2742-2901", tokens.getConditionParameter().getFirst().getValue());
                assertEquals(OPTION_PRINT, tokens.getConditionParameter().getFirst().getOption1());
                assertEquals(EMPTY, tokens.getConditionParameter().getFirst().getOption2());
                assertEquals(EMPTY, tokens.getConditionParameter().getFirst().getOption3());
            }

            @Test
            @DisplayName("단일_둘째_옵션만_있는_CMD를_잘파싱하는지_확인")
            void testCMDwithOption2() {
                String line = "DEL, ,-f, ,phoneNum,010-2742-2901";

                TokenGroup tokens = commandParser.parse(line);

                assertEquals(DEL, tokens.getType());

                assertEquals(PHONE_NUM, tokens.getConditionParameter().getFirst().getFieldName());
                assertEquals("010-2742-2901", tokens.getConditionParameter().getFirst().getValue());
                assertEquals(EMPTY, tokens.getConditionParameter().getFirst().getOption1());
                assertEquals(OPTION_FIRST_NAME, tokens.getConditionParameter().getFirst().getOption2());
                assertEquals(EMPTY, tokens.getConditionParameter().getFirst().getOption3());
            }

            @Test
            @DisplayName("단일_셋째_옵션만_있는_CMD를_잘파싱하는지_확인")
            void testCMDwithOption3() {
                String line = "SCH, , ,-s,phoneNum,010-2742-2901";

                TokenGroup tokens = commandParser.parse(line);

                assertEquals(SCH, tokens.getType());

                assertEquals(PHONE_NUM, tokens.getConditionParameter().getFirst().getFieldName());
                assertEquals("010-2742-2901", tokens.getConditionParameter().getFirst().getValue());
                assertEquals(EMPTY, tokens.getConditionParameter().getFirst().getOption1());
                assertEquals(EMPTY, tokens.getConditionParameter().getFirst().getOption2());
                assertEquals(OPTION_SMALL_SIGN, tokens.getConditionParameter().getFirst().getOption3());
            }
        }

        @Nested
        @DisplayName("다중 옵션")
        class OptionMultiple {
            @Test
            @DisplayName("첫번째_두번째_옵션이_있는_CMD를_잘파싱하는지_확인")
            void testCMDwithOption1and2() {
                String line = "DEL,-p,-f, ,phoneNum,010-2742-2901";

                TokenGroup tokens = commandParser.parse(line);
                assertEquals(DEL, tokens.getType());

                assertEquals(PHONE_NUM, tokens.getConditionParameter().getFirst().getFieldName());
                assertEquals("010-2742-2901", tokens.getConditionParameter().getFirst().getValue());
                assertEquals(OPTION_PRINT, tokens.getConditionParameter().getFirst().getOption1());
                assertEquals(OPTION_FIRST_NAME, tokens.getConditionParameter().getFirst().getOption2());
                assertEquals(EMPTY, tokens.getConditionParameter().getFirst().getOption3());
            }

            @Test
            @DisplayName("첫번째_세번째_옵션이_있는_CMD를_잘파싱하는지_확인")
            void testCMDwithOption1and3() {
                String line = "SCH,-p, ,-s,cl,CL3";

                TokenGroup tokens = commandParser.parse(line);

                assertEquals(SCH, tokens.getType());

                assertEquals(CL, tokens.getConditionParameter().getFirst().getFieldName());
                assertEquals("CL3", tokens.getConditionParameter().getFirst().getValue());
                assertEquals(OPTION_PRINT, tokens.getConditionParameter().getFirst().getOption1());
                assertEquals(EMPTY, tokens.getConditionParameter().getFirst().getOption2());
                assertEquals(OPTION_SMALL_SIGN, tokens.getConditionParameter().getFirst().getOption3());
            }
        }

        @Nested
        @DisplayName("And OR 옵션")
        class OptionAndOr {
            @Test
            @DisplayName("첫번째_옵션은_있고_두번째_옵션은_없는_경우_DEL를_잘파싱하는지_확인")
            void testDELOption2withoutOption3() {
                String line = "DEL,-p,-l, ,name,KIM,-a, , ,cl,CL4";

                TokenGroup tokens = commandParser.parse(line);

                assertEquals(DEL, tokens.getType());
                assertEquals(OPTION_AND, tokens.getConditionParameter().getOperator());

                assertEquals(NAME, tokens.getConditionParameter().getFirst().getFieldName());
                assertEquals("KIM", tokens.getConditionParameter().getFirst().getValue());
                assertEquals(OPTION_PRINT, tokens.getConditionParameter().getFirst().getOption1());
                assertEquals(OPTION_LAST_PHONE, tokens.getConditionParameter().getFirst().getOption2());
                assertEquals(EMPTY, tokens.getConditionParameter().getFirst().getOption3());

                assertEquals(CL, tokens.getConditionParameter().getSecond().getFieldName());
                assertEquals("CL4", tokens.getConditionParameter().getSecond().getValue());
                assertEquals(OPTION_AND, tokens.getConditionParameter().getSecond().getOption1());
                assertEquals(EMPTY, tokens.getConditionParameter().getSecond().getOption2());
                assertEquals(EMPTY, tokens.getConditionParameter().getSecond().getOption3());

            }

            @Test
            @DisplayName("첫번째_옵션은_있고_두번째_옵션도_있는_경우_DEL를_잘파싱하는지_확인")
            void testDELOption2withOption3() {
                String line = "DEL, ,-m, ,phoneNum,9777,-o,-y, ,birthday,1990";

                TokenGroup tokens = commandParser.parse(line);
                assertEquals(DEL, tokens.getType());

                assertEquals(PHONE_NUM, tokens.getConditionParameter().getFirst().getFieldName());
                assertEquals("9777", tokens.getConditionParameter().getFirst().getValue());
                assertEquals(EMPTY, tokens.getConditionParameter().getFirst().getOption1());
                assertEquals(OPTION_MONTH, tokens.getConditionParameter().getFirst().getOption2());
                assertEquals(EMPTY, tokens.getConditionParameter().getFirst().getOption3());

                assertEquals(BIRTHDAY, tokens.getConditionParameter().getSecond().getFieldName());
                assertEquals("1990", tokens.getConditionParameter().getSecond().getValue());
                assertEquals(OPTION_OR, tokens.getConditionParameter().getSecond().getOption1());
                assertEquals(OPTION_YEAR, tokens.getConditionParameter().getSecond().getOption2());
                assertEquals(EMPTY, tokens.getConditionParameter().getSecond().getOption3());

                assertEquals(OPTION_OR, tokens.getConditionParameter().getOperator());
            }

            @Test
            @DisplayName("첫번째_옵션은_있고_두번째_옵션은_없는_경우_MOD를_잘파싱하는지_확인")
            void testMODOption2withoutOption3() {
                String line = "MOD, ,-d, ,birthday,06,-o, , ,certi,PRO,birthday,19900906";

                TokenGroup tokens = commandParser.parse(line);

                assertEquals(MOD, tokens.getType());

                assertEquals(BIRTHDAY, tokens.getConditionParameter().getFirst().getFieldName());
                assertEquals("06", tokens.getConditionParameter().getFirst().getValue());
                assertEquals(EMPTY, tokens.getConditionParameter().getFirst().getOption1());
                assertEquals(OPTION_DAY, tokens.getConditionParameter().getFirst().getOption2());
                assertEquals(EMPTY, tokens.getConditionParameter().getFirst().getOption3());

                assertEquals(CERTI, tokens.getConditionParameter().getSecond().getFieldName());
                assertEquals("PRO", tokens.getConditionParameter().getSecond().getValue());
                assertEquals(OPTION_OR, tokens.getConditionParameter().getSecond().getOption1());
                assertEquals(EMPTY, tokens.getConditionParameter().getSecond().getOption2());
                assertEquals(EMPTY, tokens.getConditionParameter().getSecond().getOption3());

                assertEquals(BIRTHDAY, tokens.getConditionParameter().getModifyValue().getFieldName());
                assertEquals("19900906", tokens.getConditionParameter().getModifyValue().getValue());

                assertEquals(OPTION_OR, tokens.getConditionParameter().getOperator());
            }

            @Test
            @DisplayName("첫번째_옵션은_있고_두번째_옵션은_있는_경우_MOD를_잘파싱하는지_확인")
            void testMODOption2withOption3() {
                String line = "MOD,-p, , ,cl,CL2,-a,-m, ,birthday,01,name,KYUMOKLEE";

                TokenGroup tokens = commandParser.parse(line);

                assertEquals(MOD, tokens.getType());
                assertEquals(CL, tokens.getConditionParameter().getFirst().getFieldName());
                assertEquals("CL2", tokens.getConditionParameter().getFirst().getValue());
                assertEquals(OPTION_PRINT, tokens.getConditionParameter().getFirst().getOption1());
                assertEquals(EMPTY, tokens.getConditionParameter().getFirst().getOption2());
                assertEquals(EMPTY, tokens.getConditionParameter().getFirst().getOption3());

                assertEquals(BIRTHDAY, tokens.getConditionParameter().getSecond().getFieldName());
                assertEquals("01", tokens.getConditionParameter().getSecond().getValue());
                assertEquals(OPTION_AND, tokens.getConditionParameter().getSecond().getOption1());
                assertEquals(OPTION_MONTH, tokens.getConditionParameter().getSecond().getOption2());
                assertEquals(EMPTY, tokens.getConditionParameter().getSecond().getOption3());

                assertEquals(NAME, tokens.getConditionParameter().getModifyValue().getFieldName());
                assertEquals("KYUMOKLEE", tokens.getConditionParameter().getModifyValue().getValue());

                assertEquals(OPTION_AND, tokens.getConditionParameter().getOperator());
            }
        }
    }
}
